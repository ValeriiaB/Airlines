package com.example.service;

import com.example.DBase.Flight;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FinderTest {
    private Finder finder;
    private FlightRepository mockFlightRepository = mock(FlightRepository.class);
    private TicketsRepository mockTicketRepository = mock(TicketsRepository.class);

    @Before
    public void setUp() throws Exception {
        finder = new Finder();
        finder.setFlightRepository(mockFlightRepository);
        finder.setTicketsRepository(mockTicketRepository);

    }

    @Test
    public void shouldReturnAndIncreaseTackedWhenCapacityIsBiggerThanTacked(){
        when(mockFlightRepository.findByIdFlight(1L)).thenReturn(getFlightWithCapacity(15L));
        when(mockTicketRepository.countByIdFlight(1L)).thenReturn(10L);
        long ans = finder.findFree(1L);
        assertEquals(ans, 11L);
    }
    @Test
    public void shouldReturnDiffereceBetweenCapacityAndTackedWhenTackedIsBiggerThanCapacity(){
        when(mockFlightRepository.findByIdFlight(1L)).thenReturn(getFlightWithCapacity(10L));
        when(mockTicketRepository.countByIdFlight(1L)).thenReturn(15L);
        long ans = finder.findFree(1L);
        assertEquals(ans, -5);
    }

    private Flight getFlightWithCapacity(Long capacity){
        Flight flight = new Flight();
        flight.setCapacity(capacity);
        return flight;
    }
}