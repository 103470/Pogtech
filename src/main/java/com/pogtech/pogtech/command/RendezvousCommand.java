package com.pogtech.pogtech.command;


import com.pogtech.pogtech.data.CurrentUser;
import com.pogtech.pogtech.data.Rendezvous;
import com.pogtech.pogtech.database.DatabaseException;

import java.time.LocalDate;

public class RendezvousCommand implements Command {

    private final String rendezvousDate;

    public RendezvousCommand(String rendezvousDate) {
        this.rendezvousDate = rendezvousDate;
    }

    @Override
    public void execute() {
        String name = CurrentUser.getName();
        String email = CurrentUser.getEmail();
        LocalDate rendezvousLocalDate = LocalDate.parse(rendezvousDate);

        Rendezvous rendezvous = new Rendezvous(generateUniqueId(), name, email, rendezvousLocalDate);


        System.out.println("Rendezvous mentve: " + name + " - " + rendezvousDate);
    }

    private int generateUniqueId() {
        return (int) (Math.random() * 1000000);
    }
}