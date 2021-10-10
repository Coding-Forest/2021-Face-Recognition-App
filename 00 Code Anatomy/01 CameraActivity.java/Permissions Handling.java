
public abstract class CameraActivity extends AppCompatActivity {
  
  ...

  private static final int PERMISSIONS_REQUEST = 1;

  private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;

  ...

  @Override
  protected void onCreate(final Bundle savedInstanceState) {

                               // Boolan function for status check
    if (hasPermission()) {     // If permission is granted, 
      setFragment();           // set Fragment
    } else {
      requestPermission();
    }

  ...

  }
  
  
  ...
  
  // androidx.fragment.app.FragmentActivity attribute
  @Override
  // Takes action - A. if permission granted, set Fragment. 
  //                B. if not granted, requets permission.
  public void onRequestPermissionsResult(final int requestCode, 
                                         final String[] permissions, 
                                         final int[] grantResults) 
  
  // private static final int PERMISSIONS_REQUEST = 1;
    
  {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == PERMISSIONS_REQUEST) {
      if (allPermissionsGranted(grantResults)) {
        setFragment();  // void function
      } else {
        requestPermission();
      }
    }
  }

  
  /* PERMISSION_GRANTED: Permission check result: this is returned by checkPermission(String, String) 
  if the permission has been granted to the "given package".
  
  Constant Value: 0 (0x00000000) 
  
  java for (:) syntax - https://stackoverflow.com/questions/14374707/what-does-the-colon-mean-in-java/14374760
  */
  private static boolean allPermissionsGranted(final int[] grantResults) {
    for (int result : grantResults) {                         // same as: for (int gR = 0; gR < result.length; gR++)
      if (result != PackageManager.PERMISSION_GRANTED) {      // PackageManager: Class for retrieving various kinds of information 
        return false;                                         // related to the application packages that are currently installed on the device. 
      }
    }
    return true;
  }

  
  
  /*
  The SDK version of the software currently running on this hardware device. 
  This value never changes while a device is booted, but it may increase when the hardware manufacturer provides an OTA update.

  Possible values are defined in Build.VERSION_CODES
  */
  
  // public int checkSelfPermission(String permission): attribute of android.content.ContextWrapper
  private boolean hasPermission() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {      
      // private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
      // public static final int PERMISSION_GRANTED = 0 (android.content.pm.PackageManager)
      // Could return true, could return false
      return checkSelfPermission(PERMISSION_CAMERA) == PackageManager.PERMISSION_GRANTED;
    } else {
      return true;
    }
  }

  /* https://stackoverflow.com/questions/47480732/what-is-the-purpose-of-the-condition-if-build-version-sdk-int-build-version
  Q by Jaime Montoya: "What is the purpose of the condition?"
  
  A by CommonsWare
  To avoid executing that block of code on devices older than Android 8.0. 
  Notification channels were added in Android 8.0. 
  Attempting to call createNotificationChannel() on older devices would result in a crash, as that method will not exist.

  This is a standard backwards-compatibility recipe. 
  Frequently, utility classes hide this stuff (e.g., most of the classes named ...Compat in the SDK), 
  but sometimes, as is the case here, we get to do it ourselves.
  */
  private void requestPermission() {
    //  a standard backwards-compatibility recipe
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // M for Marshmallow. If SDK version is higher than the device...
      // public boolean shouldShowRequestPermissionRationale(String permission) from android.app.Activity
      // Gets whether you should show UI with rationale before requesting a permission. 
      if (shouldShowRequestPermissionRationale(PERMISSION_CAMERA)) {
        // First, inform user that permissions are required.
        Toast.makeText(
                CameraActivity.this,
                "Permissions for camera are required to run this application.",
                Toast.LENGTH_LONG)
            .show();
      }
      // Then ask for permissions
      requestPermissions(new String[] {PERMISSION_CAMERA}, PERMISSIONS_REQUEST);
    }
  }
