package br.com.alura.easybill.security;

import br.com.alura.easybill.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiracao;

    @Value("${forum.jwt.secret}")
    private String segredo;

    public String gerarToken(Authentication autenticacao) {
        Usuario usuarioLogado = (Usuario) autenticacao.getPrincipal();
        Date dataLogada = new Date();
        Date dataExpiracao = new Date(dataLogada.getTime() + Long.parseLong(expiracao));

        return Jwts.builder()
                .setIssuer("API Easybill")
                .setSubject(usuarioLogado.getId().toString())
                .setIssuedAt(dataLogada)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, segredo)
                .compact();
    }
}
