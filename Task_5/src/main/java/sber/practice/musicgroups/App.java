package sber.practice.musicgroups;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sber.practice.musicgroups.domain.AlbumEntity;
import sber.practice.musicgroups.domain.GroupEntity;
import sber.practice.musicgroups.domain.TrackEntity;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // Оставлено для дальнейших возможных тестов

//    @Bean
//    public CommandLineRunner dataLoader(CatalogService catalogService) {
//        return args -> {
//            catalogService.createGroup(
//                    new GroupEntity(1, "The Cat Empire",
//                        new ArrayList<>(Arrays.asList(
//                                new AlbumEntity(1, "Thunder Rumbles", 2023,
//                                        new ArrayList<>(Arrays.asList(new TrackEntity(1, "Thunder Rumbles", 175)))),
//                                new AlbumEntity(2, "Cities", 2021,
//                                        new ArrayList<>(Arrays.asList(
//                                                new TrackEntity(1, "Cities", 203),
//                                                new TrackEntity(2, "Boogaloo", 210),
//                                                new TrackEntity(3, "Siente", 163),
//                                                new TrackEntity(4, "Motion", 223),
//                                                new TrackEntity(5, "Song for the Day", 227)
//                                        ))),
//                                new AlbumEntity(3, "So Many Nights", 2007,
//                                        new ArrayList<>(Arrays.asList(
//                                                new TrackEntity(1, "So Many Nights", 213),
//                                                new TrackEntity(2, "Panama", 195),
//                                                new TrackEntity(3, "Fishies", 190)
//                                        )))
//                        ))));
//            catalogService.createGroup(
//                    new GroupEntity(2, "Ours Samplus",
//                            new ArrayList<>(Arrays.asList(
//                                    new AlbumEntity(1, "Orphan Loops", 2018,
//                                            new ArrayList<>(Arrays.asList(
//                                                    new TrackEntity(1, "Alignement planétaire", 156),
//                                                    new TrackEntity(2, "Blue Bird", 232),
//                                                    new TrackEntity(3, "Deepin Hill", 216),
//                                                    new TrackEntity(4, "Mad flavour", 212),
//                                                    new TrackEntity(5, "Minor alpha", 178)
//                                            ))),
//                                    new AlbumEntity(2, "Tour de manège, Vol. 1", 2013,
//                                            new ArrayList<>(Arrays.asList(
//                                                    new TrackEntity(1, "Prélude", 161),
//                                                    new TrackEntity(2, "Tournez manège", 161),
//                                                    new TrackEntity(3, "Boiseries", 198),
//                                                    new TrackEntity(4, "Ergonomie transcendantale", 193),
//                                                    new TrackEntity(5, "Over", 245)
//                                            ))),
//                                    new AlbumEntity(3, "Empty Building", 2021,
//                                            new ArrayList<>(Arrays.asList(
//                                                    new TrackEntity(1, "Leave It", 204),
//                                                    new TrackEntity(2, "Nosteke", 168),
//                                                    new TrackEntity(3, "Whut!", 120),
//                                                    new TrackEntity(4, "Ceramic", 144),
//                                                    new TrackEntity(5, "Sleepy", 159)
//                                            )))
//                            ))));
//        };
//    }
}
