package dicoding.com.moviecataloguejetpack.data.source;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import dicoding.com.moviecataloguejetpack.data.source.local.LocalRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.data.source.remote.ApiResponse;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiClient;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiInterface;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.GetTrendingMovie;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.GetTrendingTV;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.MovieResponse;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.TVResponse;
import dicoding.com.moviecataloguejetpack.utils.AppExecutors;
import dicoding.com.moviecataloguejetpack.utils.MovieHelper;
import dicoding.com.moviecataloguejetpack.vo.Resource;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeMovieRepository implements MovieDataSource {
    private ApiInterface mApiInterface;
    private volatile static FakeMovieRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final AppExecutors appExecutors;

    public FakeMovieRepository(@NonNull LocalRepository localRepository, AppExecutors appExecutors){
        this.localRepository = localRepository;
        this.appExecutors = appExecutors;
        try {
            mockWebServer.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiInterface = retrofit.create(ApiInterface.class);
//        mApiInterface = ApiClient.cteateService(ApiInterface.class);
    }

    public static FakeMovieRepository getInstance(LocalRepository localRepository, AppExecutors appExecutors){
        if(INSTANCE == null){
            synchronized (MovieRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new FakeMovieRepository(localRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    String josnMovie = "{\"page\":1,\"results\":[{\"id\":423204,\"video\":false,\"vote_count\":623,\"vote_average\":5.8,\"title\":\"Angel Has Fallen\",\"release_date\":\"2019-08-21\",\"original_language\":\"en\",\"original_title\":\"Angel Has Fallen\",\"genre_ids\":[28],\"backdrop_path\":\"/k2WyDw2NTUIWnuEs5gT7wgrCQg6.jpg\",\"adult\":false,\"overview\":\"After the events in the previous film, Secret Service agent Mike Banning finds himself framed for an assassination attempt on the President. Pursued by his own agency and the FBI, Banning races to clear his name and uncover the real terrorist threat which has set its sights on Air Force One.\",\"poster_path\":\"/fapXd3v9qTcNBTm39ZC4KUVQDNf.jpg\",\"popularity\":133.409,\"media_type\":\"movie\"},{\"id\":475557,\"video\":false,\"vote_count\":5860,\"vote_average\":8.4,\"title\":\"Joker\",\"release_date\":\"2019-10-02\",\"original_language\":\"en\",\"original_title\":\"Joker\",\"genre_ids\":[80,18,53],\"backdrop_path\":\"/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg\",\"adult\":false,\"overview\":\"During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.\",\"poster_path\":\"/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg\",\"popularity\":275.986,\"media_type\":\"movie\"},{\"id\":474350,\"video\":false,\"vote_count\":2079,\"vote_average\":6.9,\"title\":\"It Chapter Two\",\"release_date\":\"2019-09-04\",\"original_language\":\"en\",\"original_title\":\"It Chapter Two\",\"genre_ids\":[18,27],\"backdrop_path\":\"/8moTOzunF7p40oR5XhlDvJckOSW.jpg\",\"adult\":false,\"overview\":\"27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.\",\"poster_path\":\"/zfE0R94v1E8cuKAerbskfD3VfUt.jpg\",\"popularity\":166.693,\"media_type\":\"movie\"},{\"id\":466272,\"video\":false,\"vote_count\":2701,\"vote_average\":7.6,\"title\":\"Once Upon a Time... in Hollywood\",\"release_date\":\"2019-07-25\",\"original_language\":\"en\",\"original_title\":\"Once Upon a Time... in Hollywood\",\"genre_ids\":[35,18,53],\"backdrop_path\":\"/nGJpQCAn2NKeDoEflLI5DIvsqoQ.jpg\",\"adult\":false,\"overview\":\"A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles.\",\"poster_path\":\"/8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg\",\"popularity\":186.598,\"media_type\":\"movie\"},{\"id\":384018,\"video\":false,\"vote_count\":1979,\"vote_average\":6.6,\"title\":\"Fast \\u0026 Furious Presents: Hobbs \\u0026 Shaw\",\"release_date\":\"2019-08-01\",\"original_language\":\"en\",\"original_title\":\"Fast \\u0026 Furious Presents: Hobbs \\u0026 Shaw\",\"genre_ids\":[28,12,35],\"backdrop_path\":\"/qAhedRxRYWZAgZ8O8pHIl6QHdD7.jpg\",\"adult\":false,\"overview\":\"Ever since US Diplomatic Security Service Agent Hobbs and lawless outcast Shaw first faced off, they just have swapped smacks and bad words. But when cyber-genetically enhanced anarchist Brixton's ruthless actions threaten the future of humanity, both join forces to defeat him. (A spin-off of “The Fate of the Furious,” focusing on Johnson's Luke Hobbs and Statham's Deckard Shaw.)\",\"poster_path\":\"/kvpNZAQow5es1tSY6XW2jAZuPPG.jpg\",\"popularity\":99.127,\"media_type\":\"movie\"},{\"id\":420818,\"video\":false,\"vote_count\":3647,\"vote_average\":7.1,\"title\":\"The Lion King\",\"release_date\":\"2019-07-12\",\"original_language\":\"en\",\"original_title\":\"The Lion King\",\"genre_ids\":[12,18],\"backdrop_path\":\"/nRXO2SnOA75OsWhNhXstHB8ZmI3.jpg\",\"adult\":false,\"overview\":\"Simba idolizes his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.\",\"poster_path\":\"/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg\",\"popularity\":242.03,\"media_type\":\"movie\"},{\"id\":453405,\"video\":false,\"vote_count\":653,\"vote_average\":5.7,\"title\":\"Gemini Man\",\"release_date\":\"2019-10-02\",\"original_language\":\"en\",\"original_title\":\"Gemini Man\",\"genre_ids\":[28,53],\"backdrop_path\":\"/c3F4P2oauA7IQmy4hM0OmRt2W7d.jpg\",\"adult\":false,\"overview\":\"Ageing assassin, Henry Brogen tries to get out of the business but finds himself in the ultimate battle—fighting his own clone who is 25 years younger than him, and at the peak of his abilities.\",\"poster_path\":\"/uTALxjQU8e1lhmNjP9nnJ3t2pRU.jpg\",\"popularity\":154.24,\"media_type\":\"movie\"},{\"id\":429617,\"video\":false,\"vote_count\":5105,\"vote_average\":7.6,\"title\":\"Spider-Man: Far from Home\",\"release_date\":\"2019-06-28\",\"original_language\":\"en\",\"original_title\":\"Spider-Man: Far from Home\",\"genre_ids\":[28,12,878],\"backdrop_path\":\"/5myQbDzw3l8K9yofUXRJ4UTVgam.jpg\",\"adult\":false,\"overview\":\"Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.\",\"poster_path\":\"/lcq8dVxeeOqHvvgcte707K0KVx5.jpg\",\"popularity\":458.513,\"media_type\":\"movie\"},{\"id\":330457,\"video\":false,\"vote_count\":167,\"vote_average\":6.8,\"title\":\"Frozen II\",\"release_date\":\"2019-11-20\",\"original_language\":\"en\",\"original_title\":\"Frozen II\",\"genre_ids\":[12,16,35,10402,10751],\"backdrop_path\":\"/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg\",\"adult\":false,\"overview\":\"Elsa, Anna, Kristoff and Olaf are going far in the forest to know the truth about an ancient mystery of their kingdom.\",\"poster_path\":\"/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg\",\"popularity\":435.669,\"media_type\":\"movie\"},{\"id\":499701,\"video\":false,\"vote_count\":332,\"vote_average\":6.4,\"title\":\"Dora and the Lost City of Gold\",\"release_date\":\"2019-08-08\",\"original_language\":\"en\",\"original_title\":\"Dora and the Lost City of Gold\",\"genre_ids\":[12,35,10751],\"backdrop_path\":\"/61sbyO47yIpsMFyLhY1MWcqjg1Q.jpg\",\"adult\":false,\"overview\":\"Dora, a girl who has spent most of her life exploring the jungle with her parents, now must navigate her most dangerous adventure yet: high school. Always the explorer, Dora quickly finds herself leading Boots (her best friend, a monkey), Diego, and a rag tag group of teens on an adventure to save her parents and solve the impossible mystery behind a lost Inca civilization.\",\"poster_path\":\"/xvYCZ740XvngXK0FNeSNVTJJJ5v.jpg\",\"popularity\":57.585,\"media_type\":\"movie\"},{\"id\":508965,\"video\":false,\"vote_count\":194,\"vote_average\":8.6,\"title\":\"Klaus\",\"release_date\":\"2019-11-08\",\"original_language\":\"en\",\"original_title\":\"Klaus\",\"genre_ids\":[12,16,35,10751],\"backdrop_path\":\"/mlxKite1x1PgmIhJgAxNS9eHmH8.jpg\",\"adult\":false,\"overview\":\"When Jesper distinguishes himself as the Postal Academy's worst student, he is sent to Smeerensburg, a small village located on a icy island above the Arctic Circle, where grumpy inhabitants barely exchange words, let alone letters. Jesper is about to give up and abandon his duty as a postman when he meets local teacher Alva and Klaus, a mysterious carpenter who lives alone in a cabin full of handmade toys.\",\"poster_path\":\"/q125RHUDgR4gjwh1QkfYuJLYkL.jpg\",\"popularity\":83.866,\"media_type\":\"movie\"},{\"id\":567609,\"video\":false,\"vote_count\":327,\"vote_average\":6.9,\"title\":\"Ready or Not\",\"release_date\":\"2019-08-21\",\"original_language\":\"en\",\"original_title\":\"Ready or Not\",\"genre_ids\":[35,27,9648,53],\"backdrop_path\":\"/oKX8t52F25bkWUWVXiAUfTXFnLA.jpg\",\"adult\":false,\"overview\":\"A bride's wedding night takes a sinister turn when her eccentric new in-laws force her to take part in a terrifying game.\",\"poster_path\":\"/vOl6shtL0wknjaIs6JdKCpcHvg8.jpg\",\"popularity\":60.516,\"media_type\":\"movie\"},{\"id\":512895,\"video\":false,\"vote_count\":63,\"vote_average\":7.0,\"title\":\"Lady and the Tramp\",\"release_date\":\"2019-11-12\",\"original_language\":\"en\",\"original_title\":\"Lady and the Tramp\",\"genre_ids\":[35,10749],\"backdrop_path\":\"/73curw674iTzTX81AGaj5dyZUX5.jpg\",\"adult\":false,\"overview\":\"The love story between a pampered Cocker Spaniel named Lady and a streetwise mongrel named Tramp. Lady finds herself out on the street after her owners have a baby and is saved from a pack by Tramp, who tries to show her to live her life footloose and collar-free.\",\"poster_path\":\"/kzJ4a0ITuMJDuX2IjFKYG6QIipW.jpg\",\"popularity\":33.709,\"media_type\":\"movie\"},{\"id\":454640,\"video\":false,\"vote_count\":314,\"vote_average\":6.4,\"title\":\"The Angry Birds Movie 2\",\"release_date\":\"2019-08-02\",\"original_language\":\"en\",\"original_title\":\"The Angry Birds Movie 2\",\"genre_ids\":[12,16,35,10751],\"backdrop_path\":\"/k7sE3loFwuU2mqf7FbZBeE3rjBa.jpg\",\"adult\":false,\"overview\":\"Red, Chuck, Bomb and the rest of their feathered friends are surprised when a green pig suggests that they put aside their differences and unite to fight a common threat. Aggressive birds from an island covered in ice are planning to use an elaborate weapon to destroy the fowl and swine.\",\"poster_path\":\"/ebe8hJRCwdflNQbUjRrfmqtUiNi.jpg\",\"popularity\":35.722,\"media_type\":\"movie\"},{\"id\":522938,\"video\":false,\"vote_count\":549,\"vote_average\":5.9,\"title\":\"Rambo: Last Blood\",\"release_date\":\"2019-09-19\",\"original_language\":\"en\",\"original_title\":\"Rambo: Last Blood\",\"genre_ids\":[28,53],\"backdrop_path\":\"/spYx9XQFODuqEVoPpvaJI1ksAVt.jpg\",\"adult\":false,\"overview\":\"When John Rambo's niece travels to Mexico to find the father that abandoned her and her mother, she finds herself in the grasps of Calle Mexican sex traffickers. When she doesn't return home as expected, John learns she's crossed into Mexico and sets out to get her back and make them pay.\",\"poster_path\":\"/kTQ3J8oTTKofAVLYnds2cHUz9KO.jpg\",\"popularity\":61.314,\"media_type\":\"movie\"},{\"id\":484641,\"video\":false,\"vote_count\":615,\"vote_average\":6.4,\"title\":\"Anna\",\"release_date\":\"2019-06-19\",\"original_language\":\"fr\",\"original_title\":\"Anna\",\"genre_ids\":[28,53],\"backdrop_path\":\"/pIL9e5cN6RNugDCCLwn3stfb9HE.jpg\",\"adult\":false,\"overview\":\"Beneath Anna Poliatova's striking beauty lies a secret that will unleash her indelible strength and skill to become one of the world's most feared government assassins.\",\"poster_path\":\"/oZcISsiWY4LiKERTV2alu8RWYdq.jpg\",\"popularity\":76.715,\"media_type\":\"movie\"},{\"id\":299534,\"video\":false,\"vote_count\":10269,\"vote_average\":8.3,\"title\":\"Avengers: Endgame\",\"release_date\":\"2019-04-24\",\"original_language\":\"en\",\"original_title\":\"Avengers: Endgame\",\"genre_ids\":[28,12,878],\"backdrop_path\":\"/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg\",\"adult\":false,\"overview\":\"After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.\",\"poster_path\":\"/or06FN3Dka5tukK1e9sl16pB3iy.jpg\",\"popularity\":53.464,\"media_type\":\"movie\"},{\"id\":472674,\"video\":false,\"vote_count\":59,\"vote_average\":6.9,\"title\":\"The Goldfinch\",\"release_date\":\"2019-09-12\",\"original_language\":\"en\",\"original_title\":\"The Goldfinch\",\"genre_ids\":[18],\"backdrop_path\":\"/uF4gCZHiui3UJ2BzUb8Nr6De6Lt.jpg\",\"adult\":false,\"overview\":\"A boy in New York is taken in by a wealthy family after his mother is killed in a bombing at the Metropolitan Museum of Art. In a rush of panic, he steals 'The Goldfinch', a painting that eventually draws him into a world of crime.\",\"poster_path\":\"/8NwONmcJmPr0w9A176JorJ3tbJx.jpg\",\"popularity\":41.67,\"media_type\":\"movie\"},{\"id\":611207,\"video\":false,\"vote_count\":79,\"vote_average\":6.8,\"title\":\"The Knight Before Christmas\",\"release_date\":\"2019-11-21\",\"original_language\":\"en\",\"original_title\":\"The Knight Before Christmas\",\"genre_ids\":[35,10749],\"backdrop_path\":\"/ymwyqmIee4RwdvZP7R14OsmGSRG.jpg\",\"adult\":false,\"overview\":\"A movie about a medieval English knight who is magically transported to present day where he ends up falling for a high school science teacher.\",\"poster_path\":\"/ivymBQYzYqZoLhvgYg77gSzXKsA.jpg\",\"popularity\":88.28,\"media_type\":\"movie\"},{\"id\":301528,\"video\":false,\"vote_count\":3060,\"vote_average\":7.6,\"title\":\"Toy Story 4\",\"release_date\":\"2019-06-19\",\"original_language\":\"en\",\"original_title\":\"Toy Story 4\",\"genre_ids\":[12,16,35,14,10751],\"backdrop_path\":\"/m67smI1IIMmYzCl9axvKNULVKLr.jpg\",\"adult\":false,\"overview\":\"Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \\\"Forky\\\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.\",\"poster_path\":\"/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg\",\"popularity\":56.611,\"media_type\":\"movie\"}],\"total_pages\":1000,\"total_results\":20000}";
    String jsonTV = "{\"page\":1,\"results\":[{\"original_name\":\"The Mandalorian\",\"id\":82856,\"name\":\"The Mandalorian\",\"vote_count\":96,\"vote_average\":7.7,\"first_air_date\":\"2019-11-12\",\"poster_path\":\"/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg\",\"genre_ids\":[10759,10765],\"original_language\":\"en\",\"backdrop_path\":\"/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg\",\"overview\":\"Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.\",\"origin_country\":[\"US\"],\"popularity\":760.158,\"media_type\":\"tv\"},{\"original_name\":\"See\",\"id\":80752,\"name\":\"See\",\"vote_count\":80,\"vote_average\":7.7,\"first_air_date\":\"2019-11-01\",\"poster_path\":\"/g3JsScc7mQCfc3e5e5rXwu7xVVP.jpg\",\"genre_ids\":[18,10759,10765],\"original_language\":\"en\",\"backdrop_path\":\"/8TOkxONO3TEeJRuZWb0hG7SboyV.jpg\",\"overview\":\"A virus has decimated humankind. Those who survived emerged blind. Centuries later when twins are born with the mythic ability to see, their father must protect his tribe against a threatened queen.\",\"origin_country\":[\"US\"],\"popularity\":109.025,\"media_type\":\"tv\"},{\"original_name\":\"His Dark Materials\",\"id\":68507,\"name\":\"His Dark Materials\",\"vote_count\":63,\"vote_average\":7.8,\"first_air_date\":\"2019-11-03\",\"poster_path\":\"/xOjRNnQw5hqR1EULJ2iHkGwJVA4.jpg\",\"genre_ids\":[18,10765],\"original_language\":\"en\",\"backdrop_path\":\"/9yKCJTOh9m3Lol2RY3kw99QPH6x.jpg\",\"overview\":\"Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.\",\"origin_country\":[\"GB\"],\"popularity\":181.75,\"media_type\":\"tv\"},{\"original_name\":\"Watchmen\",\"id\":79788,\"name\":\"Watchmen\",\"vote_count\":65,\"vote_average\":7.3,\"first_air_date\":\"2019-10-20\",\"poster_path\":\"/m8rWq3j73ZGhDuSCZWMMoE9ePH1.jpg\",\"genre_ids\":[80,18,10759,10765],\"original_language\":\"en\",\"backdrop_path\":\"/gFeaXBnOO14aOQhMQrr5tbyhMTw.jpg\",\"overview\":\"Set in an alternate history where “superheroes” are treated as outlaws, “Watchmen” embraces the nostalgia of the original groundbreaking graphic novel while attempting to break new ground of its own.\",\"origin_country\":[\"US\"],\"popularity\":49.791,\"media_type\":\"tv\"},{\"original_name\":\"The Walking Dead\",\"id\":1402,\"name\":\"The Walking Dead\",\"vote_count\":4366,\"vote_average\":7.3,\"first_air_date\":\"2010-10-31\",\"poster_path\":\"/reKs8y4mPwPkZG99ZpbKRhBPKsX.jpg\",\"genre_ids\":[18,10759,10765],\"original_language\":\"en\",\"backdrop_path\":\"/wXXaPMgrv96NkH8KD1TMdS2d7iq.jpg\",\"overview\":\"Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.\",\"origin_country\":[\"US\"],\"popularity\":103.613,\"media_type\":\"tv\"},{\"original_name\":\"Rick and Morty\",\"id\":60625,\"name\":\"Rick and Morty\",\"vote_count\":1506,\"vote_average\":8.6,\"first_air_date\":\"2013-12-02\",\"poster_path\":\"/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg\",\"genre_ids\":[16,35,10759,10765],\"original_language\":\"en\",\"backdrop_path\":\"/mzzHr6g1yvZ05Mc7hNj3tUdy2bM.jpg\",\"overview\":\"Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.\",\"origin_country\":[\"US\"],\"popularity\":447.085,\"media_type\":\"tv\"},{\"original_name\":\"Game of Thrones\",\"id\":1399,\"name\":\"Game of Thrones\",\"vote_count\":6493,\"vote_average\":8.1,\"first_air_date\":\"2011-04-17\",\"poster_path\":\"/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg\",\"genre_ids\":[18,10759,10765],\"original_language\":\"en\",\"backdrop_path\":\"/suopoADq0k8YZr4dQXcU6pToj6s.jpg\",\"overview\":\"Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.\",\"origin_country\":[\"US\"],\"popularity\":98.473,\"media_type\":\"tv\"},{\"original_name\":\"The Flash\",\"id\":60735,\"name\":\"The Flash\",\"vote_count\":3003,\"vote_average\":6.7,\"first_air_date\":\"2014-10-07\",\"poster_path\":\"/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg\",\"genre_ids\":[18,10765],\"original_language\":\"en\",\"backdrop_path\":\"/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg\",\"overview\":\"After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.\",\"origin_country\":[\"US\"],\"popularity\":175.302,\"media_type\":\"tv\"},{\"original_name\":\"Arrow\",\"id\":1412,\"name\":\"Arrow\",\"vote_count\":2840,\"vote_average\":5.8,\"first_air_date\":\"2012-10-10\",\"poster_path\":\"/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg\",\"genre_ids\":[80,18,9648,10759],\"original_language\":\"en\",\"backdrop_path\":\"/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg\",\"overview\":\"Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.\",\"origin_country\":[\"US\"],\"popularity\":251.817,\"media_type\":\"tv\"},{\"original_name\":\"Titans\",\"id\":75450,\"name\":\"Titans\",\"vote_count\":352,\"vote_average\":7.6,\"first_air_date\":\"2018-10-12\",\"poster_path\":\"/eeHI5iBSCOxj4HGSOmFM6azBmwb.jpg\",\"genre_ids\":[18,10759,10765],\"original_language\":\"en\",\"backdrop_path\":\"/7DE9KC9GyY2TUJMnSPgYJG3rafw.jpg\",\"overview\":\"A team of young superheroes led by Nightwing (formerly Batman's first Robin) form to combat evil and other perils.\",\"origin_country\":[\"US\"],\"popularity\":102.03,\"media_type\":\"tv\"},{\"original_name\":\"The Crown\",\"id\":65494,\"name\":\"The Crown\",\"vote_count\":305,\"vote_average\":8.1,\"first_air_date\":\"2016-11-04\",\"poster_path\":\"/uT4HL776Up8LgjqfmmGtGKSDgvT.jpg\",\"genre_ids\":[18],\"original_language\":\"en\",\"backdrop_path\":\"/p3qBwzzLe9aw1yA0s4hg0M2DvD0.jpg\",\"overview\":\"The gripping, decades-spanning inside story of Her Majesty Queen Elizabeth II and the Prime Ministers who shaped Britain's post-war destiny. \\n\\nThe Crown tells the inside story of two of the most famous addresses in the world – Buckingham Palace and 10 Downing Street – and the intrigues, love lives and machinations behind the great events that shaped the second half of the 20th century. Two houses, two courts, one Crown.\",\"origin_country\":[\"GB\",\"US\"],\"popularity\":48.917,\"media_type\":\"tv\"},{\"original_name\":\"Mr. Robot\",\"id\":62560,\"name\":\"Mr. Robot\",\"vote_count\":1596,\"vote_average\":7.9,\"first_air_date\":\"2015-05-27\",\"poster_path\":\"/oKIBhzZzDX07SoE2bOLhq2EE8rf.jpg\",\"genre_ids\":[80,18],\"original_language\":\"en\",\"backdrop_path\":\"/4n0TZfTUSUELqRrOA8sZKWs9bWU.jpg\",\"overview\":\"A contemporary and culturally resonant drama about a young programmer, Elliot, who suffers from a debilitating anti-social disorder and decides that he can only connect to people by hacking them. He wields his skills as a weapon to protect the people that he cares about. Elliot will find himself in the intersection between a cybersecurity firm he works for and the underworld organizations that are recruiting him to bring down corporate America.\",\"origin_country\":[\"US\"],\"popularity\":69.723,\"media_type\":\"tv\"},{\"original_name\":\"The Good Doctor\",\"id\":71712,\"name\":\"The Good Doctor\",\"vote_count\":442,\"vote_average\":7.7,\"first_air_date\":\"2017-09-25\",\"poster_path\":\"/53P8oHo9cfOsgb1cLxBi4pFY0ja.jpg\",\"genre_ids\":[18],\"original_language\":\"en\",\"backdrop_path\":\"/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg\",\"overview\":\"A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?\",\"origin_country\":[\"US\"],\"popularity\":67.965,\"media_type\":\"tv\"},{\"original_name\":\"The Man in the High Castle\",\"id\":62017,\"name\":\"The Man in the High Castle\",\"vote_count\":397,\"vote_average\":7.5,\"first_air_date\":\"2015-01-15\",\"poster_path\":\"/xhoDZbMNeyCA0BGPZQsdIiO43Dp.jpg\",\"genre_ids\":[18,10765],\"original_language\":\"en\",\"backdrop_path\":\"/nsVueoSwrgZBxlFaankiPIhNRPH.jpg\",\"overview\":\"Explore what it would be like if the Allied Powers had lost WWII, and Japan and Germany ruled the United States. Based on Philip K. Dick's award-winning novel.\",\"origin_country\":[\"US\"],\"popularity\":57.635,\"media_type\":\"tv\"},{\"original_name\":\"Supergirl\",\"id\":62688,\"name\":\"Supergirl\",\"vote_count\":1092,\"vote_average\":6.0,\"first_air_date\":\"2015-10-26\",\"poster_path\":\"/4ka8vAzAFUZFKxWyfGfwVcSXuZo.jpg\",\"genre_ids\":[28,12,18,878],\"original_language\":\"en\",\"backdrop_path\":\"/3x1lVyxtsLKsZyR2E3qRe1EAXG4.jpg\",\"overview\":\"Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.\",\"origin_country\":[\"US\"],\"popularity\":91.896,\"media_type\":\"tv\"},{\"original_name\":\"The Blacklist\",\"id\":46952,\"name\":\"The Blacklist\",\"vote_count\":1098,\"vote_average\":7.0,\"first_air_date\":\"2013-09-23\",\"poster_path\":\"/bgbQCW4fE9b6wSOSC6Fb4FfVzsW.jpg\",\"genre_ids\":[80,18,9648],\"original_language\":\"en\",\"backdrop_path\":\"/fHwiAqIKragaCbNJo9Qs4lrccIN.jpg\",\"overview\":\"Raymond \\\"Red\\\" Reddington, one of the FBI's most wanted fugitives, surrenders in person at FBI Headquarters in Washington, D.C. He claims that he and the FBI have the same interests: bringing down dangerous criminals and terrorists. In the last two decades, he's made a list of criminals and terrorists that matter the most but the FBI cannot find because it does not know they exist. Reddington calls this \\\"The Blacklist\\\". Reddington will co-operate, but insists that he will speak only to Elizabeth Keen, a rookie FBI profiler.\",\"origin_country\":[\"US\"],\"popularity\":120.551,\"media_type\":\"tv\"},{\"original_name\":\"Riverdale\",\"id\":69050,\"name\":\"Riverdale\",\"vote_count\":734,\"vote_average\":7.3,\"first_air_date\":\"2017-01-26\",\"poster_path\":\"/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg\",\"genre_ids\":[18,9648],\"original_language\":\"en\",\"backdrop_path\":\"/2IUpoKSP64r6rp2vBo0Fdk8a1UU.jpg\",\"overview\":\"Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.\",\"origin_country\":[\"US\"],\"popularity\":119.356,\"media_type\":\"tv\"},{\"original_name\":\"The Morning Show\",\"id\":90282,\"name\":\"The Morning Show\",\"vote_count\":23,\"vote_average\":7.5,\"first_air_date\":\"2019-11-01\",\"poster_path\":\"/qnKGIa6MsLIhze1znD0HLriqmxv.jpg\",\"genre_ids\":[18],\"original_language\":\"en\",\"backdrop_path\":\"/3iqul4h9YXhDIJU7MOBAMk0zUXb.jpg\",\"overview\":\"A behind-the-scenes look at the lives of the people who help America wake up in the morning, exploring the unique challenges faced by the men and women who carry out this daily televised ritual.\",\"origin_country\":[\"US\"],\"popularity\":39.865,\"media_type\":\"tv\"},{\"original_name\":\"Grey's Anatomy\",\"id\":1416,\"name\":\"Grey's Anatomy\",\"vote_count\":1124,\"vote_average\":6.4,\"first_air_date\":\"2005-03-27\",\"poster_path\":\"/jnsvc7gCKocXnrTXF6p03cICTWb.jpg\",\"genre_ids\":[18],\"original_language\":\"en\",\"backdrop_path\":\"/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg\",\"overview\":\"Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.\",\"origin_country\":[\"US\"],\"popularity\":127.458,\"media_type\":\"tv\"},{\"original_name\":\"The War of the Worlds\",\"id\":83981,\"name\":\"The War of the Worlds\",\"vote_count\":6,\"vote_average\":6.7,\"first_air_date\":\"2019-11-17\",\"poster_path\":\"/zcUkFaxUtP3V3YjoS1fh8YRW19H.jpg\",\"genre_ids\":[18,10765],\"original_language\":\"en\",\"backdrop_path\":\"/xLh6Lkj0J5d4new50BxDHEB7wjj.jpg\",\"overview\":\"In Edwardian England, George and his partner Amy attempt to defy society and start a life together as they face the escalating terror of an alien invasion, fighting for their lives against an enemy beyond their comprehension.\",\"origin_country\":[\"GB\"],\"popularity\":30.838,\"media_type\":\"tv\"}],\"total_pages\":1000,\"total_results\":20000}";
    String movieContent = "{\"adult\":false,\"backdrop_path\":\"/k2WyDw2NTUIWnuEs5gT7wgrCQg6.jpg\",\"belongs_to_collection\":{\"id\":386534,\"name\":\"... Has Fallen Collection\",\"poster_path\":\"/25gcpe1vwzl2VwaAq5rgzHePoxo.jpg\",\"backdrop_path\":\"/o9jZvL6pi7ur9k5cumSAQUAbmzw.jpg\"},\"budget\":70000000,\"genres\":[{\"id\":28,\"name\":\"Action\"}],\"homepage\":\"https://angelhasfallen.movie/\",\"id\":423204,\"imdb_id\":\"tt6189022\",\"original_language\":\"en\",\"original_title\":\"Angel Has Fallen\",\"overview\":\"After the events in the previous film, Secret Service agent Mike Banning finds himself framed for an assassination attempt on the President. Pursued by his own agency and the FBI, Banning races to clear his name and uncover the real terrorist threat which has set its sights on Air Force One.\",\"popularity\":133.409,\"poster_path\":\"/fapXd3v9qTcNBTm39ZC4KUVQDNf.jpg\",\"production_companies\":[{\"id\":1020,\"logo_path\":\"/kuUIHNwMec4dwOLghDhhZJzHZTd.png\",\"name\":\"Millennium Films\",\"origin_country\":\"US\"},{\"id\":48738,\"logo_path\":null,\"name\":\"Campbell Grobman Films\",\"origin_country\":\"US\"},{\"id\":71488,\"logo_path\":null,\"name\":\"Eclectic Pictures\",\"origin_country\":\"\"},{\"id\":103331,\"logo_path\":null,\"name\":\"G-BASE\",\"origin_country\":\"US\"}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"release_date\":\"2019-08-21\",\"revenue\":0,\"runtime\":121,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"}],\"Status\":\"Released\",\"tagline\":\"Loyalty Is Under Fire. Fallen. Framed. Forced to Fight for His Life.\",\"title\":\"Angel Has Fallen\",\"video\":false,\"vote_average\":5.8,\"vote_count\":660}";
    String tvContent = "{\"backdrop_path\":\"/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg\",\"created_by\":[{\"id\":15277,\"credit_id\":\"5bb6f5c39251410dc601d77f\",\"name\":\"Jon Favreau\",\"gender\":2,\"profile_path\":\"/rOVBKURoR7TrG8MYxTuNUFj3E68.jpg\"}],\"episode_run_time\":[35],\"first_air_date\":\"2019-11-12\",\"genres\":[{\"id\":10765,\"name\":\"Sci-Fi & Fantasy\"},{\"id\":10759,\"name\":\"Action & Adventure\"}],\"homepage\":\"https://disneyplusoriginals.disney.com/show/the-mandalorian\",\"id\":82856,\"in_production\":true,\"languages\":[\"nl\",\"en\"],\"last_air_date\":\"2019-11-22\",\"last_episode_to_air\":{\"air_date\":\"2019-11-22\",\"episode_number\":3,\"id\":1980403,\"name\":\"Chapter 3: The Sin\",\"overview\":\"The battered Mandalorian returns to his client for reward.\",\"production_code\":\"\",\"season_number\":1,\"show_id\":82856,\"still_path\":\"/5wGkymV2D9P9ZGI2wP0PefIwFol.jpg\",\"vote_average\":9.25,\"vote_count\":4},\"name\":\"The Mandalorian\",\"next_episode_to_air\":{\"air_date\":\"2019-11-29\",\"episode_number\":4,\"id\":1980404,\"name\":\"Chapter 4\",\"overview\":\"The Mandalorian teams up with an ex-soldier to protect a village from raiders.\",\"production_code\":\"\",\"season_number\":1,\"show_id\":82856,\"still_path\":null,\"vote_average\":0.0,\"vote_count\":0},\"networks\":[{\"name\":\"Disney+\",\"id\":2739,\"logo_path\":\"/gJ8VX6JSu3ciXHuC2dDGAo2lvwM.png\",\"origin_country\":\"US\"}],\"number_of_episodes\":8,\"number_of_seasons\":1,\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Mandalorian\",\"overview\":\"Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.\",\"popularity\":760.158,\"poster_path\":\"/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg\",\"production_companies\":[{\"id\":1,\"logo_path\":\"/o86DbpburjxrqAzEDhXZcyE8pDb.png\",\"name\":\"Lucasfilm\",\"origin_country\":\"US\"},{\"id\":109755,\"logo_path\":\"/hUCbTgfDPp1sIo8BOI0AaOMx1Si.png\",\"name\":\"Walt Disney Studios\",\"origin_country\":\"US\"}],\"seasons\":[{\"air_date\":\"2019-11-12\",\"episode_count\":8,\"id\":110346,\"name\":\"Season 1\",\"overview\":\"\",\"poster_path\":\"/sUTqIb82LxYhPT0SfI8AR03GLpz.jpg\",\"season_number\":1}],\"Status\":\"Returning Series\",\"type\":\"Scripted\",\"vote_average\":7.6,\"vote_count\":119}";

    MockWebServer mockWebServer = new MockWebServer();

    @Override
    public LiveData<Resource<List<MovieEntity>>> getMovies() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors){
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                MutableLiveData<ApiResponse<List<MovieResponse>>> mutableLiveData = new MutableLiveData<>();
                mockWebServer.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(josnMovie));
                Call<GetTrendingMovie> TrendingCall = mApiInterface.getTrending("trending", "movie",
                        "week", ApiClient.MyApi);
                List<MovieResponse> responseList = null;
                try {
                    responseList = TrendingCall.execute().body().getListTrendingMovies();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mutableLiveData.setValue(ApiResponse.success(responseList));
                return mutableLiveData;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                ArrayList<MovieEntity> movList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    MovieResponse responseMov = data.get(i);
                    String genre = responseMov.getGenreMovie() == null ? "" : MovieHelper.List2String(responseMov.getGenreMovie());
                    MovieEntity movie = new MovieEntity(responseMov.getIdMovie(), responseMov.getTitleMovie(),
                            genre, responseMov.getDurasiMovie(),
                            responseMov.getReleaseMovie(), responseMov.getPopularity(),
                            responseMov.getDetailMovie(), responseMov.getPosterMovie(), "",
                            responseMov.getPoint() , "", "", false);
                    movList.add(movie);
                }
                localRepository.insertMovieList(movList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TvEntity>>> getTvies() {
        return new NetworkBoundResource<List<TvEntity>, List<TVResponse>>(appExecutors){
            @Override
            protected LiveData<List<TvEntity>> loadFromDB() {
                return localRepository.getAllTV();
            }

            @Override
            protected Boolean shouldFetch(List<TvEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TVResponse>>> createCall() {
                MutableLiveData<ApiResponse<List<TVResponse>>> mutableLiveData = new MutableLiveData<>();
                mockWebServer.enqueue(new MockResponse().setBody(jsonTV));
                Call<GetTrendingTV> TrendingCall = mApiInterface.getTrendingTV("trending", "tv",
                        "week", ApiClient.MyApi);
                List<TVResponse> responseList = null;
                try {
                    responseList = TrendingCall.execute().body().getTrendingTV();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mutableLiveData.setValue(ApiResponse.success(responseList));
                return mutableLiveData;
            }

            @Override
            protected void saveCallResult(List<TVResponse> data) {
                ArrayList<TvEntity> tvList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    TVResponse responseTV = data.get(i);
                    String genre = responseTV.getGenreTV() == null ? "" : MovieHelper.List2String(responseTV.getGenreTV());
                    int durasi = responseTV.getDurasiTV() == null ? 0 : responseTV.getDurasiTV().get(0);
                    TvEntity tv = new TvEntity(responseTV.getIdTV(), responseTV.getTitleTV(), responseTV.getDetailTV(),
                            "", responseTV.getPoint(), "", responseTV.getPosterTV(), genre, durasi,
                            responseTV.getFirstAirDate(), responseTV.getNumberSeasons(), responseTV.getPopularity(),
                            false);
                    tvList.add(tv);
                }
                localRepository.insertTvList(tvList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovContent(int idmov) {
        return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors){

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovieById(idmov);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                mockWebServer.enqueue(new MockResponse().setBody(movieContent));
                MutableLiveData<ApiResponse<MovieResponse>> movieMutableLiveData = new MutableLiveData<>();
                Call<MovieResponse> MovieCall = mApiInterface.getDetailsMov("movie", idmov, ApiClient.MyApi);//, Locale.getDefault()
                MovieResponse movieResponse = null;
                try {
                    movieResponse = MovieCall.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                movieMutableLiveData.setValue(ApiResponse.success(movieResponse));
                return movieMutableLiveData;
            }

            @Override
            protected void saveCallResult(MovieResponse data) {
                String genre = data.getGenreMovie() == null ? "" : MovieHelper.List2String(data.getGenreMovie());
                MovieEntity movie = new MovieEntity(data.getIdMovie(), data.getTitleMovie(),
                        genre, data.getDurasiMovie(),
                        data.getReleaseMovie(), data.getPopularity(),
                        data.getDetailMovie(), data.getPosterMovie(), "",
                        data.getPoint() , "", "", false);
                localRepository.updateMovie(movie);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvEntity>> getTvContent(int idtv) {
        return new NetworkBoundResource<TvEntity, TVResponse>(appExecutors){
            @Override
            protected LiveData<TvEntity> loadFromDB() {
                return localRepository.getTVById(idtv);
            }

            @Override
            protected Boolean shouldFetch(TvEntity data) {
                return data.getDurasitv() == 0;
            }

            @Override
            protected LiveData<ApiResponse<TVResponse>> createCall() {
                mockWebServer.enqueue(new MockResponse().setBody(tvContent));
                MutableLiveData<ApiResponse<TVResponse>> tvShowMutableLiveData = new MutableLiveData<>();
                Call<TVResponse> TvCall = mApiInterface.getDetailsTV("tv", idtv, ApiClient.MyApi);//Locale.getDefault()
                TVResponse tvResponse = null;
                try {
                    tvResponse = TvCall.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tvShowMutableLiveData.postValue(ApiResponse.success(tvResponse));
                return tvShowMutableLiveData;
            }

            @Override
            protected void saveCallResult(TVResponse data) {
                String genre = data.getGenreTV() == null ? "" : MovieHelper.List2String(data.getGenreTV());
                int durasi = data.getDurasiTV() == null ? 0 : data.getDurasiTV().get(0);
                TvEntity tv = new TvEntity(data.getIdTV(), data.getTitleTV(), data.getDetailTV(),
                        "", data.getPoint(), "", data.getPosterTV(), genre, durasi,
                        data.getFirstAirDate(), data.getNumberSeasons(), data.getPopularity(),
                        false);
            }
        }.asLiveData();
    }

    @Override
    public void updateFavMovie(int id, Boolean fav) {
        Runnable runnable = () -> localRepository.updateFavMovie(id, fav);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updateFavTV(int id, Boolean fav) {
        Runnable runnable = () -> localRepository.updateFavTv(id, fav);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getPagedFavoriteMovie() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors){
            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getPagedFavMovie(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvEntity>>> getPagedFavoriteTv() {
        return new NetworkBoundResource<PagedList<TvEntity>, List<TVResponse>>(appExecutors){
            @Override
            protected LiveData<PagedList<TvEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getPagedFavTv(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TVResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVResponse> data) {

            }
        }.asLiveData();
    }
}
