package ru.netology.ticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket ticket1 = new Ticket(1, 3000, "DME", "LED", 100);
    Ticket ticket2 = new Ticket(2, 2490, "DME", "LED", 100);
    Ticket ticket3 = new Ticket(3, 2990, "VKO", "LED", 85);
    Ticket ticket4 = new Ticket(4, 2935, "DME", "LED", 80);

    @BeforeEach
    public void setUp() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
    }

    @Test
    public void shouldFindAllTickets() {

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4};
        Ticket[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTickets() {

        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("", "");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByFromTo() {
        Ticket[] expected = {ticket2, ticket4, ticket1};
        Ticket[] actual = manager.searchBy("DME", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByVko() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.searchBy("VKO", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeById() {
        repo.removeById(3);

        Ticket[] expected = {ticket1, ticket2, ticket4};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}
