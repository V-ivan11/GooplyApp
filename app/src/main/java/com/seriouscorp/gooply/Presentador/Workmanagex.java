package com.seriouscorp.gooply.Presentador;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.seriouscorp.gooply.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Workmanagex extends Worker {
    public Workmanagex(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public static void guardarNoti(Long duration, Data data, String tag){
        OneTimeWorkRequest noti = new OneTimeWorkRequest.Builder(Workmanagex.class)
                .setInitialDelay(duration, TimeUnit.MILLISECONDS).addTag(tag)
                .setInputData(data).build();
        WorkManager intance = WorkManager.getInstance();
        intance.enqueue(noti);
    }

        @NonNull
        @Override
        public Result doWork() {

            String titulo = getInputData().getString("titulo");
            String detalle = getInputData().getString("detalle");
            int id = (int) getInputData().getLong("idnoti", 0);

            notnot(titulo, detalle);//fua xd

            return Result.success();
        }

        public void  notnot(String t, String d) {
            String id = "message";
            NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), id);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel nc = new NotificationChannel(id, "nuevo", NotificationManager.IMPORTANCE_HIGH);
                nc.setDescription("Noticación FCM");
                nc.setShowBadge(true);
                assert nm != null;
                nm.createNotificationChannel(nc);
            }

            builder.setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(t)
                    .setTicker("Nueva Notificación")
                    .setSmallIcon(R.drawable.ic_pastillas)
                    .setContentText(d)
                    .setContentInfo("Nuevo");
            Random random = new Random();
            int idNotify = random.nextInt(8000);

            assert nm != null;
            nm.notify(idNotify, builder.build());
        }
    }

