package com.example.proiectjava.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.proiectjava.model.Inregistrare;

@Repository
@Transactional
public class InregistrareDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Inregistrare> list() {
        String sql = "SELECT * FROM Inregistrare";

        List<Inregistrare> listInregistrare = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Inregistrare.class));

        return listInregistrare;
    }


    public void save(Inregistrare inregistrare) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("inregistrare").usingColumns("id_inregistrare", "id_utilizator", "id_vacanta", "data_i");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(inregistrare);
        insertActor.execute(param);
    }

    public Inregistrare get(int id) {
        String sql = "SELECT * FROM Inregistrare WHERE ID_INREGISTRARE= ?";
        Object[] args = {id};
        Inregistrare inregistrare = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Inregistrare.class));
        return inregistrare;
    }

    public void update(Inregistrare inregistrare) {
        String sql = "UPDATE inregistrare SET id_inregistrare=:id_inregistrare, id_utilizator=:id_utilizator, id_vacanta=:id_vacanta, data_i=:data_i WHERE id_inregistrare=:id_inregistrare";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(inregistrare);

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM inregistrare WHERE ID_INREGISTRARE = ?";
        jdbcTemplate.update(sql, id);
    }
}



