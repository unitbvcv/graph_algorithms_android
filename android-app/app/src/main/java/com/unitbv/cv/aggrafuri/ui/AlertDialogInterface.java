package com.unitbv.cv.aggrafuri.ui;

import android.content.Context;
import android.view.View;

public interface AlertDialogInterface {

    View onBuildDialog(Context context);
    void onCancel();
    void onResult(View view);

}
