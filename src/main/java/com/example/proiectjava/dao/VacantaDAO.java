package com.example.proiectjava.dao;

import java.util.List;

import com.example.proiectjava.model.Atractie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.proiectjava.model.Vacanta;

@Repository
@Transactional
public class VacantaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AtractieDAO atractieDAO;
    
    public List<Vacanta> list() {
        String sql = "SELECT * FROM Vacanta";

        List<Vacanta> listVacanta = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Vacanta.class));

        return listVacanta;
    }

    public void save(Vacanta vacanta) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);

        String currentUser;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            currentUser = ((UserDetails)principal).getUsername();
        } else {
            currentUser = principal.toString();
        }
        vacanta.setAutor(currentUser);
        insertActor.withTableName("vacanta").usingColumns("id_vacanta", "tara", "durata", "cost","autor");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(vacanta);
        insertActor.execute(param);
    }

    public Vacanta get(int id) {
        String sql = "SELECT * FROM Vacanta WHERE ID_VACANTA = ?";
        Object[] args = {id};
        Vacanta vacanta = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Vacanta.class));
        return vacanta;
    }

    public List<Atractie> getAllAttractions(int id){
        List<Atractie> listaAtractii = atractieDAO.getByVacanta(id);
        return  listaAtractii;
    }

    public void update(Vacanta vacanta) {
        String sql = "UPDATE vacanta SET id_vacanta=:id_vacanta, tara=:tara, durata=:durata, cost=:cost WHERE id_vacanta=:id_vacanta";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(vacanta);

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Vacanta WHERE ID_VACANTA = ?";


        String sql2 = "SELECT * FROM Vacanta WHERE ID_VACANTA = ?";
        Object[] args = {id};
        Vacanta vacanta = jdbcTemplate.queryForObject(sql2, args,
                BeanPropertyRowMapper.newInstance(Vacanta.class));

        String currentUser;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            currentUser = ((UserDetails)principal).getUsername();
        } else {
            currentUser = principal.toString();
        }

        if(currentUser.compareTo(vacanta.getAutor())== 0){
            jdbcTemplate.update(sql, id);
        }
    }
}


