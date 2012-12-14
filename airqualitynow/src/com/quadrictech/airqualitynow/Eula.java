package com.quadrictech.airqualitynow;
/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */


import com.quadrictech.airqualitynow.settings.AppPreferences;
import com.quadrictech.airqualitynow.settings.IPreferences;
import com.quadrictech.airqualitynow.utils.ResourceUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * This class handles display of EULAs ("End User License Agreements") to the
 * user.
 */
class Eula {
  
  private Eula() {}

  /**
   * Displays the EULA if necessary. This method should be called from the
   * onCreate() method of your main Activity.  If the user accepts, the EULA
   * will never be displayed again.  If the user refuses, the activity will
   * finish (exit).
   *
   * @param activity The Activity to finish if the user rejects the EULA
   */
  static void showEulaRequireAcceptance(final Activity activity) {
    final IPreferences preferences = new AppPreferences(activity.getBaseContext());
    
    if(preferences.getEulaAccepted()){
    	return;
    }

    final AlertDialog.Builder builder = initDialog(activity);
    builder.setPositiveButton(R.string.accept,
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            accept(activity, preferences);
          }
        });
    builder.setNegativeButton(R.string.decline,
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            refuse(activity);
          }
        });
    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
      public void onCancel(DialogInterface dialog) {
        refuse(activity);
      }
    });
    builder.show();
  }

  /**
   * Display the EULA to the user in an informational context.  They won't be
   * given the choice of accepting or declining the EULA -- we're simply
   * displaying it for them to read.
   */
  static void showEula(Context context) {
    AlertDialog.Builder builder = initDialog(context);
    builder.setPositiveButton(R.string.ok, null);
    builder.show();
  }
  
  private static AlertDialog.Builder initDialog(Context context) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(true);
    builder.setTitle(R.string.eula_title);
    builder.setMessage(ResourceUtils.readFile(context, R.raw.eula));
    return builder;
  }

  private static void accept(Activity activity, IPreferences preferences) {
    preferences.setEulaAccepted();
  }

  private static void refuse(Activity activity) {
    activity.finish();
  }
}
