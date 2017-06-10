package com.example.Controllers;

import com.example.Authentication.SecurityService;
import com.example.DBase.Tickets;
import com.example.DBase.Users;
import com.example.service.Finder;
import com.example.service.TicketUpdates;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserControllerTest {

    private UserController userController;
    private SecurityService mockSecurityService = mock(SecurityService.class);
    private Finder mockFinder = mock(Finder.class);
    private TicketUpdates mockTicketUpdates = mock(TicketUpdates.class);

    @Before
    public void setUp() throws Exception {
        userController = new UserController();
        userController.setFinder(mockFinder);
        userController.setSecurityService(mockSecurityService);
        userController.setTicketUpdates(mockTicketUpdates);
    }
    @Test
    public void shouldReturnBadRequestStatusWhenPlaceIsEqualsZero(){
        when(mockFinder.findFree(1L)).thenReturn(0L);
        when(mockSecurityService.getAuthenticatedUser()).thenReturn(getUserById(1L));
        ResponseEntity result = userController.booking(1L);
        assertEquals(result, new ResponseEntity( HttpStatus.BAD_REQUEST));
    }
    @Test
    public void shouldReturnBadRequestStatusWhenPlaceIsLessThanZero(){
        when(mockFinder.findFree(1L)).thenReturn(-5L);
        when(mockSecurityService.getAuthenticatedUser()).thenReturn(getUserById(1L));
        ResponseEntity result = userController.booking(1L);
        assertEquals(result, new ResponseEntity( HttpStatus.BAD_REQUEST));
    }
    @Test
    public void shouldReturnOkStatusWhenPlaceIsBiggerThanZero(){
        when(mockFinder.findFree(1L)).thenReturn(15L);
        when(mockSecurityService.getAuthenticatedUser()).thenReturn(getUserById(1L));
        when(mockTicketUpdates.createTicket(1L, 1L,15L)).thenReturn(new Tickets());
        ResponseEntity result = userController.booking(1L);
        assertEquals(result, new ResponseEntity(HttpStatus.OK));
    }

    private Users getUserById(Long id){
        Users user = new Users();
        user.setIdUser(id);
        return user;
    }
}