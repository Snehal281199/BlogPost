package com.example.demo.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

     @Autowired
    private JwtTokenHelper jwtHelper;


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //Authorization

        String requestToken = request.getHeader("Authorization");
        //Bearer 2352345235sdfrsfgsdfsdf
        System.out.println(requestToken);
        String username = null;
        String token = null;
        if (requestToken != null && requestToken.startsWith("Bearer")) {
        	
        	
        		token = requestToken.substring(7);
        		
        	
            try {

                username = this.jwtHelper.getUsernameFromToken(token);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT token !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
            	 System.out.println(" jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
            	 System.out.println("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }


        } else {
        	System.out.println ("Token does not begin with the bearer ");
        }


        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from user name
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
            	System.out.println("Invalid JWT Token !!");
            }


        }
        else {
        	System.out.println("username is numm or context is not null");
        }

        filterChain.doFilter(request, response);


    }

	
}