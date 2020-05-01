package pl.jwichniarek.travel.repository;

import pl.jwichniarek.travel.model.Client;
import pl.jwichniarek.travel.model.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientRepositoryTest {

    private List<Reservation> reservation(long id) {
        Reservation reservation = new Reservation();
        reservation.setId(id);
        return (List<Reservation>) reservation;
    }

    private final Client client1 = new Client(1L, "Jan", "Nowak", "CDR097432", reservation(1));
    private final Client client2 = new Client(2L, "Piotr", "Malysz", "POE321234", reservation(2));
    private final Client client3 = new Client(3L, "Patryk", "Koscielny", "ERT345678", reservation(3));
    private final Client client4 = new Client(4L, "Maciej", "Iluzyjny", "PWE923456", reservation(4));
    private final Client client5 = new Client(5L, "Tomek", "Borowski", "OIU765343", null);

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void shouldFindAllClients() {
        //when
        List<Client> actual = clientRepository.findAll();

        //then
        assertThat(actual).containsExactlyInAnyOrder(client1, client2, client3, client4, client5);
    }

    @Test
    public void shouldFindClientById() {
        //when
        Optional<Client> actual = clientRepository.findById(1L);

        //then
        assertThat(actual.get()).isEqualTo(client1);
    }

    @Test
    public void shouldFindClientWithFirstNameEqualToMaciej() {
        //when
        List<Client> actual = clientRepository.findByFirstName("Maciej");

        //then
        assertThat(actual).containsExactlyInAnyOrder(client4);
    }

    @Test
    @Transactional
    public void shouldAddNewClientToDatabase() {
        //given
        Client newClient = new Client(6L, "Jakub", "Wolski", "CAV009134", null);

        //when
        clientRepository.save(newClient);

        //then
        assertThat(clientRepository.findAll().contains(newClient));

    }

    @Test
    @Transactional
    public void shouldUpdateClient() {
        //given
        Client updateClient = new Client(5L, "Bartek", "Borowski", "OIU765343", null);

        //when
        clientRepository.save(updateClient);

        //then
        assertThat(clientRepository.count()).isEqualTo(5L);
        assertThat(clientRepository.findById(5L).get()).isEqualTo(updateClient);

    }

    @Test
    @Transactional
    public void shouldDeleteClient() {
        //when
        clientRepository.deleteById(3L);

        //then
        assertThat(clientRepository.findById(3L)).isEmpty();
    }
}
