package ru.mav26.vkrapp.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import ru.mav26.vkrapp.R
import java.time.Duration
import java.time.LocalTime

class TimerService : Service() {
    private var countDownTimer: CountDownTimer? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundWithNotification(initial = true)

        val targetTimeString = intent?.getStringExtra("target_time") ?: return START_NOT_STICKY
        val targetTime = LocalTime.parse(targetTimeString)

        val isTask = intent.getBooleanExtra("is_task", true)
        val itemId = intent.getStringExtra("item_id")

        startTimerUntil(targetTime, isTask, itemId)

        return START_STICKY
    }

    private fun startTimerUntil(targetTime: LocalTime, isTask: Boolean, itemId: String?) {
        val durationMillis = Duration.ofHours(targetTime.hour.toLong())
            .plusMinutes(targetTime.minute.toLong())
            .plusSeconds(targetTime.second.toLong())
            .toMillis()

        countDownTimer = object : CountDownTimer(durationMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateNotification(millisUntilFinished)
            }

            override fun onFinish() {
                updateNotification(0)
                sendCompletionBroadcast(isTask, itemId)
                stopSelf()
            }
        }.start()
    }

    private fun sendCompletionBroadcast(isTask: Boolean, itemId: String?) {
        val intent = Intent("ru.mav26.vkrapp.TIMER_FINISHED").apply {
            putExtra("is_task", isTask)
            putExtra("item_id", itemId)
        }
        sendBroadcast(intent)
    }

    private fun startForegroundWithNotification(initial: Boolean = false) {
        val channelId = "timer_channel"
        createNotificationChannel(channelId)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Таймер запущен")
            .setContentText(if (initial) "Инициализация..." else "Осталось времени: ...")
            .setSmallIcon(R.drawable.timer)
            .setOnlyAlertOnce(true)
            .build()

        startForeground(1, notification)
    }

    private fun updateNotification(millisRemaining: Long) {
        val minutes = (millisRemaining / 1000) / 60
        val seconds = (millisRemaining / 1000) % 60

        val notification = NotificationCompat.Builder(this, "timer_channel")
            .setContentTitle("Таймер")
            .setContentText(String.format("Осталось: %02d:%02d", minutes, seconds))
            .setSmallIcon(R.drawable.timer)
            .setOnlyAlertOnce(true)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    private fun createNotificationChannel(channelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Таймер"
            val descriptionText = "Уведомления от таймера"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}