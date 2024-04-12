/*
 * package com.example.demo.Firebase;
 * 
 * import java.io.FileInputStream; import java.io.IOException;
 * 
 * import org.springframework.context.annotation.Configuration;
 * 
 * import com.google.api.client.googleapis.auth.oauth2.GoogleCredential; import
 * com.google.firebase.FirebaseApp; import com.google.firebase.FirebaseOptions;
 * import com.google.firebase.auth.FirebaseCredential;
 * 
 * 
 * 
 * @Configuration public class FirebaseConfig {
 * 
 * public void initializeFirebaseApp() throws IOException{
 * 
 * FileInputStream serviceAccount = new FileInputStream(
 * "C:\\Users\\10738451\\Documents\\workspace-spring-tool-suite-4-4.21.0.RELEASE"
 * );
 * 
 * 
 * FirebaseOptions options = new FirebaseOptions.Builder()
 * .setCredential((FirebaseCredential)
 * GoogleCredential.fromStream(serviceAccount)) .build();
 * 
 * FirebaseApp.initializeApp(options);
 * 
 * 
 * }
 * 
 * }
 */