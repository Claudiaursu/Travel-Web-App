package com.example.proiectjava.security;

import com.example.proiectjava.dao.UtilizatorDAO;
import com.example.proiectjava.model.Utilizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilizatorDetailsService implements UserDetailsService {
    private UtilizatorDAO utilizatorDAO;

    public UtilizatorDetailsService(UtilizatorDAO dao){
        this.utilizatorDAO = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilizator utilizator = this.utilizatorDAO.getByEmail(s);
        UserDetails utilizatorDetails = new UtilizatorDetails(utilizator);
        return utilizatorDetails;
    }
}
