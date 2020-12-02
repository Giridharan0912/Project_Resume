package com.alphaverse.projectresume.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alphaverse.projectresume.model.Achievements;
import com.alphaverse.projectresume.model.IndustrialVisit;
import com.alphaverse.projectresume.model.PersonalDetails;
import com.alphaverse.projectresume.model.Profile;
import com.alphaverse.projectresume.model.Projects;
import com.alphaverse.projectresume.model.Qualification;
import com.alphaverse.projectresume.model.Skills;
import com.alphaverse.projectresume.model.Strengths;

@Database(entities = {Profile.class, PersonalDetails.class, Qualification.class, Skills.class, Strengths.class, Projects.class, Achievements.class, IndustrialVisit.class}, version = 1, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
public static volatile RoomDb INSTANCE;
public abstract RoomDao roomDao();

   public static RoomDb getDatabase(final Context context){
       if (INSTANCE==null){
           synchronized (RoomDb.class){
               if (INSTANCE==null){
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           RoomDb.class, "resume_creator_database")
                           .build();
               }
           }
       }
       return INSTANCE;
   }
}
