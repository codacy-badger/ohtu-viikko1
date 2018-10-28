package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static java.util.Arrays.asList;

public class StatisticsTest {

    Reader readerStub = new Reader() {
        @Override
        public List<Player> getPlayers() {
            return asList(
                    new Player("Semenko", "EDM", 4, 12),
                    new Player("Lemieux", "PIT", 45, 54),
                    new Player("Kurri",   "EDM", 37, 53),
                    new Player("Yzerman", "DET", 42, 56),
                    new Player("Gretzky", "EDM", 35, 89)
            );
        }
    };

    Statistics stats;

    @Before
    public void setup() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void pelaajaVoidaanHakeaNimella() {
        String expectedName = "Semenko";
        Player player = stats.search(expectedName);
        assertEquals(expectedName, player.getName());
    }

    @Test
    public void nullJosPelaajaaEiLoydy() {
        Player player = stats.search("foobaar");
        assertEquals(null, player);
    }

    @Test
    public void listaaTiiminPelaajat() {
        String teamName = "EDM";
        List<Player> playersInTeam = stats.team(teamName);
        playersInTeam.forEach(p -> assertEquals(teamName, p.getTeam()));
    }

    @Test
    public void listaaPelaajatPisteJarjestyksessa() {
        List<Player> players = stats.topScorers(3);
        List<Integer> expectedOrder = players.stream().map(Player::getPoints).sorted((a,b) -> b - a).collect(Collectors.toList());
        List<Integer> actualOrder = players.stream().map(Player::getPoints).collect(Collectors.toList());
        assertEquals(expectedOrder, actualOrder);
    }
}
