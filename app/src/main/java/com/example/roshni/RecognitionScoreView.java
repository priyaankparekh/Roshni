/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.roshni;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.roshni.Classifier.Recognition;

public class RecognitionScoreView extends View implements ResultsView {
  private static final float TEXT_SIZE_DIP = 14;
  private final float textSizePx;
  private final Paint fgPaint;
  private final Paint bgPaint;
  private List<Recognition> results;

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    textSizePx =
            TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = new Paint();
    fgPaint.setTextSize(textSizePx);

    bgPaint = new Paint();
    bgPaint.setColor(0xcc4285f4);
  }

  @Override
  public void setResults(final List<Recognition> results) {
    this.results = results;
    postInvalidate();
  }

  public void showMyToast(final Toast toast, final int cnt) {
    final Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        toast.show();
      }
    }, 0, 5000);
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        toast.cancel();
        timer.cancel();
      }
    }, cnt);
  }

  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 10;
    int y = (int) (fgPaint.getTextSize() * 1.5f);

    canvas.drawPaint(bgPaint);

    if (results != null) {
      for (final Recognition recog : results) {
          canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);
          y += fgPaint.getTextSize() * 1.5f;
          //System.out.println(recog.getTitle());
      }
    }
  }
}
