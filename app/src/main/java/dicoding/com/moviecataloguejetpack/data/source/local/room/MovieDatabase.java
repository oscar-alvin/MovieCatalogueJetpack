package dicoding.com.moviecataloguejetpack.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//import dicoding.com.moviecataloguejetpack.data.source.local.entity.Favorit;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;

@Database(entities = {MovieEntity.class, TvEntity.class},
        version = 1, exportSchema = false) //, Favorit.class
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase INSTANCE;
    public abstract MovieDao movieDao();
    private static final Object slock = new Object();

    public static MovieDatabase getINSTANCE(Context context){
        synchronized (slock){
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "MovieCatalogue.db").build();
            }
            return INSTANCE;
        }
    }
}
