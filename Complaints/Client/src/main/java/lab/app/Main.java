package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import lab.dto.ComplaintDTO;

import java.util.List;

public class Main {

    private static final String BASE_URL =
            "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();

        try {

            getAllComplaints(client);

            ComplaintDTO complaint = getComplaint(client, 503L);

            updateComplaintStatus(client, complaint, "closed");

            getOpenComplaints(client);

        } finally {
            client.close();
        }
    }
    private static void getAllComplaints(Client client) {

        System.out.println("=== ALL COMPLAINTS ===");

        List<ComplaintDTO> complaints =
                client.target(BASE_URL)
                        .request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<ComplaintDTO>>() {});

        complaints.forEach(Main::printComplaint);
    }

    private static ComplaintDTO getComplaint(Client client, Long id) {

        System.out.println("\n=== SINGLE COMPLAINT ===");

        ComplaintDTO complaint =
                client.target(BASE_URL + "/" + id)
                        .request(MediaType.APPLICATION_JSON)
                        .get(ComplaintDTO.class);

        Main.printComplaint(complaint);

        return complaint;
    }

    private static void updateComplaintStatus(
            Client client,
            ComplaintDTO complaint,
            String newStatus
    ) {

        System.out.println("\n=== UPDATING STATUS ===");

        complaint.setStatus(newStatus);

        client.target(BASE_URL + "/" + complaint.getId())
                .request()
                .put(Entity.entity(
                        complaint,
                        MediaType.APPLICATION_JSON
                ));

        System.out.println("Status changed to: " + newStatus);
    }

    private static void getOpenComplaints(Client client) {

        System.out.println("\n=== OPEN COMPLAINTS ===");

        List<ComplaintDTO> complaints =
                client.target(BASE_URL)
                        .queryParam("status", "open")
                        .request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<ComplaintDTO>>() {});

        complaints.forEach(Main::printComplaint);
    }
    private static void printComplaint(ComplaintDTO complaint) {

        System.out.println("---------------------------------");
        System.out.println("ID:          " + complaint.getId());
        System.out.println("Status:      " + complaint.getStatus());
        System.out.println("Text: " + complaint.getComplaintText());
        System.out.println("Author: " + complaint.getAuthor());
    }
}