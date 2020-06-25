package com.example.proiectjava.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.proiectjava.model.Utilizator;

@Repository
@Transactional
public class UtilizatorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    

    public List<Utilizator> list() {
        String sql = "SELECT * FROM Utilizator";

        List<Utilizator> listUtilizator = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Utilizator.class));

        return listUtilizator;
    }

    public void save(Utilizator utilizator) {
        utilizator.setParola(passwordEncoder.encode(utilizator.getParola()));

        if((utilizator.getNume().compareTo("Petcu") == 0 && utilizator.getPrenume().compareTo("Cristiana") == 0)  || (utilizator.getNume().compareTo("Ursu") == 0 && utilizator.getPrenume().compareTo("Claudia") == 0))
        {
            utilizator.setRoles("ADMIN");
        }
        else {
            utilizator.setRoles("USER");
        }

        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("utilizator").usingColumns("id_utilizator", "nume", "prenume", "email", "telefon","parola","roles");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(utilizator);
        insertActor.execute(param);
    }

    public Utilizator get(int id) {
        String sql = "SELECT * FROM utilizator WHERE ID_UTILIZATOR = ?";
        Object[] args = {id};
        Utilizator utilizator = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Utilizator.class));
        return utilizator;
    }

    public Utilizator getByEmail(String email){
        String sql = "SELECT * FROM utilizator WHERE email = ?";
        Object[] args = {email};
        Utilizator utilizator = jdbcTemplate.queryForObject(sql, args,
                BeanPropertyRowMapper.newInstance(Utilizator.class));
        return utilizator;
    }

    public void update(Utilizator utilizator) {
        String sql = "UPDATE utilizator SET id_utilizator=:id_utilizator, nume=:nume, prenume=:prenume, email=:email, telefon=:telefon WHERE id_utilizator=:id_utilizator";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(utilizator);

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM utilizator WHERE ID_UTILIZATOR = ?";
        jdbcTemplate.update(sql, id);
    }
}