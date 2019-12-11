package dicoding.com.moviecataloguejetpack.utils;

import java.util.ArrayList;
import java.util.List;

import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;

public class FakeDataDummy {

    public static ArrayList<MovieEntity> generateDummyMovie() {

        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity(423204, "Angel Has Fallen", "Action",
                121, "2019-08-21", 133.409f,
                "After the events in the previous film, Secret Service agent Mike Banning finds himself framed for an assassination attempt on the President. Pursued by his own agency and the FBI, Banning races to clear his name and uncover the real terrorist threat which has set its sights on Air Force One.",
                "/fapXd3v9qTcNBTm39ZC4KUVQDNf.jpg", "", 5.8f, "", "", true
        ));

        movies.add(new MovieEntity(399579, "Alita Battle Angel", "Action|Science Fiction|Thriller|Adventure",
                122, "2019-Feb-14", (float) 68,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "", "", (float) 68, "", "", true
        ));

        movies.add(new MovieEntity(297802, "Aquaman", "Action|Adventure|Fantasy",
                135, "2018-Dec-18", (float) 68,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "", "", (float) 68, "", "", false
        ));

        movies.add(new MovieEntity(424694, "Bohemian", "Drama|Music",
                122, "2018-Oct-30", (float) 81,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "", "", (float) 81, "", "", false
        ));

        movies.add(new MovieEntity(438650, "Cold Persuit", "Action",
                119, "2019-Feb-8", (float) 54,
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "", "", (float) 54, "", "", false
        ));

        movies.add(new MovieEntity(480530, "Creed II", "Drama",
                130, "2019-Nov-14", (float) 67,
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "", "", (float) 67, "", "", false
        ));

        movies.add(new MovieEntity(338952, "Fantastic Beasts: The Crimes of Grindelwald",
                "Adventure|Fantasy|Family",
                134, "2018-Nov-16", (float) 69,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "", "", (float) 69, "", "", false
        ));

        movies.add(new MovieEntity(450465, "Glass", "Thriller|Drama|Science Fiction",
                129, "2019-Jan-18", (float) 65,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "", "", (float) 65, "", "", false
        ));

        movies.add(new MovieEntity(166428, "How to Train Your Dragon: The Hidden Wold", "Animation|Family|Adventure",
                104, "2019-Feb-22", (float) 77,
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "", "", (float) 77, "", "", false
        ));

        movies.add(new MovieEntity(299536, "Avenger Infinity", "Adventure|Action|Science Fiction",
                149, "2018-Apr-23", (float) 83,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "", "", (float) 83, "", "", false
        ));

        movies.add(new MovieEntity(457136, "Mary Queen of Scots", "Drama|History",
                124, "2018-Nov-15", (float) 66,
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                "", "", (float) 66, "", "", false
        ));

        return movies;
    }

    public static ArrayList<TvEntity> generateDummyTV() {

        ArrayList<TvEntity> tvies = new ArrayList<>();

        tvies.add(new TvEntity(82856, "The Mandalorian",
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "", 7.6f, "", "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg", "Sci-Fi & Fantasy | Action & Adventure",
                40, "2019-11-12", 8, 760.158f, true
        ));

        tvies.add(new TvEntity(79501, "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "", 6.5f, "", "", "Sci-fi & Fantasy|Action & Adventure",
                60, "2019-02-15", 1, 92.591f, true
        ));

        tvies.add(new TvEntity(12609, "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure.",
                "", 7.1f, "", "", "Comedy|Sci-fi & Fantacy|Animation|Action & Adventure",
                25, "1986-02-26", 1, 74.754f, false
        ));

        tvies.add(new TvEntity(46261, "Fairy Tail",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "", 64.0f, "", "", "Action & Adventure|nimation|Comedy|Sci-fi & Fantansy",
                25, "2014-06-02", 1, 64.0f, false
        ));

        tvies.add(new TvEntity(1434, "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "", 65.0f, "", "", "Crime|Drama|Mystery|Action & Adventure",
                22, "2014-06-02", 1, 65.0f, false
        ));

        tvies.add(new TvEntity(60735, "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "", 67.0f, "", "", "Drama|Sci-fi & Fancasy",
                44, "2014-06-02", 1, 67.0f, false
        ));

        tvies.add(new TvEntity(1399, "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "", 81.0f, "", "", "Sci-fi & Fantasy|Action & Adventure",
                60, "2014-06-02", 1, 81.0f, false
        ));

        tvies.add(new TvEntity(60708, "Gotham",
                "Before there was Batman, there was GOTHAM. \\n Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "", 69.0f, "", "", "Drama|Fantasy|Crime",
                60, "2014-06-02", 1, 69.0f, false
        ));

        tvies.add(new TvEntity(1416, "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "", 63.0f, "", "", "Drama",
                43, "2014-06-02", 1, 63.0f, false
        ));

        tvies.add(new TvEntity(54155, "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "", 67.0f, "", "", "Action & Adventure|Drama",
                50, "2014-06-02", 1, 67.0f, false
        ));

        tvies.add(new TvEntity(62127, "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "", 61.0f, "", "", "Action & Adventure|Crime|Drama|Sci-fi & Fancasy",
                55, "2014-06-02", 1, 61.0f, false
        ));

        return tvies;
    }

    public static MovieEntity getMovie(int movId) {
        for (int i = 0; i < generateDummyMovie().size(); i++) {
            MovieEntity entity = generateDummyMovie().get(i);
            if (entity.getIdmovie() == movId) {
                return entity;
            }
        }
        return null;
    }

    public static TvEntity getTVshow(int tvId) {
        for (int i = 0; i < generateDummyTV().size(); i++) {
            TvEntity entity = generateDummyTV().get(i);
            if (entity.getIdtv() == tvId) {
                return entity;
            }
        }
        return null;
    }

    public static List<MovieEntity> getFavMovie() {
        List<MovieEntity> listFavMovie = new ArrayList<>();
        for (int i = 0; i < generateDummyMovie().size(); i++) {
            MovieEntity entity = generateDummyMovie().get(i);
            if (entity.getFavorite() == true) {
                listFavMovie.add(entity);
            }
        }
        return listFavMovie;
    }

    public static List<TvEntity> getFavTV() {
        List<TvEntity> listFavTV = new ArrayList<>();
        for (int i = 0; i < generateDummyTV().size(); i++) {
            TvEntity entity = generateDummyTV().get(i);
            if (entity.getFavorite() == true) {
                listFavTV.add(entity);
            }
        }
        return listFavTV;
    }
}
