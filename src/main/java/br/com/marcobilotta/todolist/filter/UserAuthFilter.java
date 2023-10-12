package br.com.marcobilotta.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marcobilotta.todolist.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

public class UserAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var userPasswordEncoded = request.getHeader("Authorization");
                byte[] authDecode = Base64.getDecoder().decode(
                        userPasswordEncoded.substring("Basic".length()).trim());
                var authString = new String(authDecode);
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];

                var user = this.userRepository.findByUsername(username);
                if (user == null) {
                    response.sendError(401,"Usuário não autorizado!");
                }
                if (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
    }
}
