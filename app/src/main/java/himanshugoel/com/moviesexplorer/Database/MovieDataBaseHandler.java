package himanshugoel.com.moviesexplorer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import himanshugoel.com.moviesexplorer.Models.DirectorSearchResponse;

/**
 * Created by Himanshu on 22-06-2017.
 */

public class MovieDataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "moviesDataBase";
    // movies table name
    private static final String TABLE_MOVIES = "movies";
    //coloumn name
    private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_SHOW_ID = "show_id";
    private static final String COLUMN_SHOW_TITLE = "show_title";
    private static final String COLUMN_RELEASE_YEAR = "release_year";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_SHOW_CAST = "show_cast";
    private static final String COLUMN_DIRECTOR = "director";
    private static final String COLUMN_SUMMARY = "summary";
    private static final String COLUMN_POSTER = "poster";
    private static final String COLUMN_MEDIATYPE = "mediatype";
    private static final String COLUMN_RUNTIME = "runtime";


    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MOVIES + "( " + COLUMN_UNIT
            + " integer, " + COLUMN_SHOW_ID
            + " integer," + COLUMN_SHOW_TITLE
            + " text," + COLUMN_RELEASE_YEAR
            + " text," + COLUMN_RATING
            + " text," + COLUMN_CATEGORY
            + " text," + COLUMN_SHOW_CAST
            + " text," + COLUMN_DIRECTOR
            + " text," + COLUMN_SUMMARY
            + " text," + COLUMN_POSTER
            + " text," + COLUMN_MEDIATYPE
            + " integer," + COLUMN_RUNTIME
            + " text" + ")";


    public MovieDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        // Create tables again
        onCreate(sqLiteDatabase);

    }

    //CRUD Operations methods


    //adding new movie data
    public void addMoviesData(DirectorSearchResponse directorSearchResponse) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_UNIT, directorSearchResponse.getUnit());
        contentValues.put(COLUMN_SHOW_ID, directorSearchResponse.getShowId());
        contentValues.put(COLUMN_SHOW_TITLE, directorSearchResponse.getShowTitle());
        contentValues.put(COLUMN_RELEASE_YEAR, directorSearchResponse.getReleaseYear());
        contentValues.put(COLUMN_RATING, directorSearchResponse.getRating());
        contentValues.put(COLUMN_CATEGORY, directorSearchResponse.getCategory());
        contentValues.put(COLUMN_SHOW_CAST, directorSearchResponse.getShowCast());
        contentValues.put(COLUMN_DIRECTOR, directorSearchResponse.getDirector());
        contentValues.put(COLUMN_SUMMARY, directorSearchResponse.getSummary());
        contentValues.put(COLUMN_POSTER, directorSearchResponse.getPoster());
        contentValues.put(COLUMN_MEDIATYPE, directorSearchResponse.getMediatype());
        contentValues.put(COLUMN_RUNTIME, directorSearchResponse.getRuntime());
        sqLiteDatabase.insert(TABLE_MOVIES, null, contentValues);
        sqLiteDatabase.close();

    }

    //getting all favourite movie data
    public List<DirectorSearchResponse> getAllMovieData() {
        List<DirectorSearchResponse> directorSearchResponseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MOVIES;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                DirectorSearchResponse directorSearchResponse = new DirectorSearchResponse();
                directorSearchResponse.setUnit(Integer.parseInt(cursor.getString(0)));
                directorSearchResponse.setShowId(Integer.parseInt(cursor.getString(1)));
                directorSearchResponse.setShowTitle(cursor.getString(2));
                directorSearchResponse.setReleaseYear(cursor.getString(3));
                directorSearchResponse.setRating(cursor.getString(4));
                directorSearchResponse.setCategory(cursor.getString(5));
                directorSearchResponse.setShowCast(cursor.getString(6));
                directorSearchResponse.setDirector(cursor.getString(7));
                directorSearchResponse.setSummary(cursor.getString(8));
                directorSearchResponse.setPoster(cursor.getString(9));
                directorSearchResponse.setMediatype(Integer.parseInt(cursor.getString(10)));
                directorSearchResponse.setRuntime(cursor.getString(11));
                directorSearchResponseList.add(directorSearchResponse);
            }
            while (cursor.moveToNext());
        }

        return directorSearchResponseList;
    }

    //deleting movie data i.e removing it from favourites
    public void deleteMovieData(DirectorSearchResponse directorSearchResponse) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, COLUMN_SHOW_ID + " = ?",
                new String[]{String.valueOf(directorSearchResponse.getShowId())});
        db.close();

    }
}
