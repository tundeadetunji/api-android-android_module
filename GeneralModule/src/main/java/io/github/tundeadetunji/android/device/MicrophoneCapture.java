package io.github.tundeadetunji.android.device;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MicrophoneCapture {

    public interface CallbackCommand {
        void onRecordingFinished(String filePath);
    }

    private AudioRecord audioRecord;
    private boolean isRecording = false;
    private String filename;
    private int duration; // Duration in milliseconds
    private CallbackCommand callback; // Callback for recording finish

    public MicrophoneCapture(int duration, TimeUnit timeUnit, String filename, CallbackCommand callback) {
        this.duration = (int) timeUnit.toMillis(duration); // Convert duration to milliseconds
        this.filename = filename;
        this.callback = callback; // Set the callback
    }

    public void start(Context context) {
        int bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);

        audioRecord.startRecording();
        isRecording = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                writeAudioDataToFile();
            }
        }).start();

        // Stop recording after the specified duration
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Log.e("AutoAudioRecorder", "Recording interrupted: " + e.getMessage());
        } finally {
            stopRecording();
        }
    }

    private void writeAudioDataToFile() {
        FileOutputStream os = null;
        String pcmFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + filename + ".pcm";
        File pcmFile = new File(pcmFilePath);

        try {
            os = new FileOutputStream(pcmFile);
            byte[] buffer = new byte[1024];
            while (isRecording) {
                int read = audioRecord.read(buffer, 0, buffer.length);
                if (read > 0) {
                    os.write(buffer, 0, read);
                }
            }
            Log.d("AutoAudioRecorder", "Audio recorded successfully: " + pcmFilePath);
        } catch (IOException e) {
            Log.e("AutoAudioRecorder", "Error writing audio data: " + e.getMessage());
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    Log.e("AutoAudioRecorder", "Error closing output stream: " + e.getMessage());
                }
            }
        }

        // Notify that recording is finished
        if (callback != null) {
            callback.onRecordingFinished(pcmFilePath);
        }
    }

    private void stopRecording() {
        if (audioRecord != null) {
            isRecording = false;
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }
}