package com.example.proiectjava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.proiectjava.model.Recenzie;
import com.example.proiectjava.model.Utilizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.proiectjava.model.Atractie;

@Repository
@Transactional
public class AtractieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RecenzieDAO recenzieDAO;

    public List<Atractie> list() {
        String sql = "SELECT * FROM ATRACTIE";

        List<Atractie> listAtractie = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Atractie.class));

        return listAtractie;
    }

    public void save(Atractie atractie) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            atractie.setAutor(username);
        } else {
            username = principal.toString();
            atractie.setAutor(username);
        }

        insertActor.withTableName("Atractie").usingColumns("id_atractie", "id_vacanta", "oras", "tip", "nume","pret","autor","poza");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(atractie);
        insertActor.execute(param);
    }

    public Atractie get(int id) {
        String sql = "SELECT * FROM ATRACTIE WHERE id_atractie = ?";
        Object[] args = {id};
        Atractie atractie = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Atractie.class));
        return atractie;
    }

    public List<Atractie>getByVacanta(int id){
        String sql = "SELECT * FROM ATRACTIE WHERE ID_VACANTA= ?";
        Object[] args = {id};
        List<Atractie> listaAtractii = jdbcTemplate.query(sql, args, new RowMapper<Atractie>() {
            @Override
            public Atractie mapRow(ResultSet rs, int i) throws SQLException {
                Atractie atractie = new Atractie();
                atractie.setId_atractie(rs.getInt("id_atractie"));
                atractie.setId_vacanta(rs.getInt("id_vacanta"));
                atractie.setNume(rs.getString("nume"));
                atractie.setOras(rs.getString("oras"));
                atractie.setPret(rs.getFloat("pret"));
                atractie.setTip(rs.getString("tip"));
                atractie.setAutor(rs.getString("autor"));
                atractie.setPoza(rs.getString("poza"));

                return atractie;
            }
        });

        return listaAtractii;
    }

    public List<Recenzie> getAllRecenzii(int id){
        List<Recenzie> listaRecenzii = recenzieDAO.getByAtractie(id);
        return listaRecenzii;
    }

    public void update(Atractie atractie) {
        String sql = "UPDATE ATRACTIE SET id_atractie=:id_atractie, id_vacanta=:id_vacanta, oras=:oras, tip=:tip, nume=:nume, pret=:pret, poza=:poza WHERE id_atractie=:id_atractie";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(atractie);

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM ATRACTIE WHERE id_atractie = ?";

        String sql2 = "SELECT * FROM ATRACTIE WHERE id_atractie = ?";
        Object[] args = {id};
        Atractie atractie = jdbcTemplate.queryForObject(sql2, args,
                BeanPropertyRowMapper.newInstance(Atractie.class));

        String currentUser;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            currentUser = ((UserDetails)principal).getUsername();
        } else {
            currentUser = principal.toString();
        }

        if(atractie.getAutor().compareTo(currentUser) == 0){
            jdbcTemplate.update(sql, id);
        }
    }

}
