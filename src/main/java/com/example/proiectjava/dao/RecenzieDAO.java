package com.example.proiectjava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.proiectjava.model.Atractie;
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
import com.example.proiectjava.model.Recenzie;


@Repository
@Transactional
public class RecenzieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Recenzie> list() {
        String sql = "SELECT * FROM RECENZIE";

        List<Recenzie> listRecenzie = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Recenzie.class));

        return listRecenzie;
    }

    public void save(Recenzie recenzie) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
            recenzie.setAutor(username);
        } else {
             username = principal.toString();
            recenzie.setAutor(username);
        }
        insertActor.withTableName("recenzie").usingColumns("id_recenzie", "id_atractie", "titlu", "nota", "parere","autor");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(recenzie);
        insertActor.execute(param);
    }

    public String getAuthor(int id){
        String sql = "SELECT * FROM RECENZIE WHERE ID_RECENZIE = ?";
        Object[] args = {id};
        Recenzie recenzie = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Recenzie.class));
        return recenzie.getAutor();
    }

    public Recenzie get(int id) {
        String sql = "SELECT * FROM RECENZIE WHERE ID_RECENZIE = ?";
        Object[] args = {id};
        Recenzie recenzie = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Recenzie.class));
        return recenzie;
    }

    public List<Recenzie>getByAtractie(int id){
        String sql = "SELECT * FROM RECENZIE WHERE ID_ATRACTIE= ?";
        Object[] args = {id};
        List<Recenzie> listaRecenzii = jdbcTemplate.query(sql, args, new RowMapper<Recenzie>() {
            @Override
            public Recenzie mapRow(ResultSet rs, int i) throws SQLException {
                Recenzie recenzie = new Recenzie();
                recenzie.setId_atractie(rs.getInt("id_atractie"));
                recenzie.setId_recenzie(rs.getInt("id_recenzie"));
                recenzie.setTitlu(rs.getString("titlu"));
                recenzie.setNota(rs.getFloat("nota"));
                recenzie.setParere(rs.getString("parere"));
                recenzie.setAutor(rs.getString("autor"));

                return recenzie;
            }
        });

        return listaRecenzii;
    }

    public void update(Recenzie recenzie) {
        String sql = "UPDATE RECENZIE SET id_recenzie=:id_recenzie, id_atractie=:id_atractie, titlu=:titlu, nota=:nota, parere=:parere WHERE id_recenzie=:id_recenzie";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(recenzie);

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM RECENZIE WHERE ID_RECENZIE = ?";

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        String sql2 = "SELECT * FROM RECENZIE WHERE ID_RECENZIE = ?";
        Object[] args = {id};
        Recenzie recenzie = jdbcTemplate.queryForObject(sql2, args,
                BeanPropertyRowMapper.newInstance(Recenzie.class));

        if(username.compareTo(recenzie.getAutor()) == 0){
            jdbcTemplate.update(sql, id);
        }
    }
}
