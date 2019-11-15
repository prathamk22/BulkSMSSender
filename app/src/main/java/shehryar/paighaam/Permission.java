package shehryar.paighaam;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    public static boolean permissionsValidate(int requestCode, Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> lPermissions = new ArrayList<>();
            //verifica as permissoes ja liberadas (uma a uma)
            for (String permission : permissions) {
                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
                if (!validaPermissao) {
                    lPermissions.add(permission);
                }
            }
            //caso a lista esteja vazia não solicitar permissao
            if (lPermissions.isEmpty()) {
                return true;
            }
            //converter para array de string
            String[] newPermissions = new String[lPermissions.size()];
            lPermissions.toArray(newPermissions);
            //solicita permissao
            ActivityCompat.requestPermissions(activity, newPermissions, requestCode);
        }
        return true;
    }
}
