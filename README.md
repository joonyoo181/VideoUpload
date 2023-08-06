# i.am Beta 
<img width="500" alt="image" src="https://user-images.githubusercontent.com/59751754/201499843-ea49c2ef-3713-41b4-9691-5aea11cb52dc.png">

## Summary
Social media app dedicated to providng a safe environment for teenagers to relieve stress in. Designed from scratch using Android Studio (for development) and Figma (for UI design). Made in Kotlin, JavaScript, and Firebase. Functionalities include fild upload, cloud messaging/notifications, form validation, user authentication, and camera.

## Features and Interfaces of this Beta
1. Sign In Page / Sign Up Page
   - Login with the provided user email and password
   - Integrated with Firebase authentication
   - Supports Google, Facebook, Twitter login
<p float="left">
  <img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/201499977-db526501-d284-4839-8952-6df20342d850.png">
  <img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/209815852-cd0e1b54-2f32-4f98-a4a3-029ef01ea142.png">
</p>

2. Forgot Password Page
   - User enters an existing email on the database -> app sends a "reset password" email to the user's email
   - Integrated with Firebase authentication
<img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/209816332-e9385c8a-14b1-4ebf-a366-199a1910bc43.png">

3. Feed Page
   - Shows a list of posts by other users
   - Utilizes a post fragment to display data from the database
   - Integrated with Firebase Firestore Database / Cloud Firestore to fetch the video, title, description, and comment button that leads to the comment section
<img height="400" alt="image" src="https://user-images.githubusercontent.com/59751754/201499756-b425bc96-28b2-432e-aae2-170aeb79aec0.png">

4. Camera / New Post / File Upload Page
   - Camera with zoom slider, flash turn on/off button, and rear/front camera switch button
   - Rotating icons if camera is turned horizontal
   - Post title, description, video, user information are hashed into Hashmaps and stored in Firebase Cloud Firestore
   - Integrated with CameraX (Jetpack library), Firebase authentication, Firebase Firestore Database
<p float="left">
  <img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/209818215-256419e3-5707-4249-826b-095e8d59d577.png">
  <img width="300" alt="image" src="https://user-images.githubusercontent.com/59751754/209818958-48ffdd49-f28f-4d08-ab64-ba3f010c0c2c.png">
  <img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/209817714-a0332458-8c9e-4708-8c87-8cc52faae018.png">
</p>

## Features and Interfaces of Current App
5. Notification Page
   - Displays a list of past notifications
   - Can accept / decline friend requests
   - Integrated with Firebase Cloud Messaging to send notifications when the user is not on the app
<img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/209820994-cbc1d12b-e80b-4dbe-806a-df843e4820e3.png">

6. Profile Page
   - Displays a user's name, tag, profile image, and biography
   - Allows a user to signout and edit their information
   - Integrated with Firebase Firestore Database
<p float="left">
  <img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/201500012-95beaf45-e809-4440-804f-301f1d05ba7f.png">
  <img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/209821942-c096d500-972f-45e2-b8ad-1e08bc59d15d.png">
</p>

7. Messages Log Page
   - Displays a list of friends (with online/offline status) whom a user can message
   - Integrated with Firebase Firestore Database
<img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/209822219-7940edd7-0453-4b5e-aa25-6f75b93e4e68.png">

8. Chat Page
   - Instagram-like chat page with timestamp display and past texts
   - Abilities to attach videos and images to messages
<img height="300" alt="image" src="https://user-images.githubusercontent.com/59751754/201499823-cd3308d1-cc1d-417b-8f45-812f9ba1bc5c.png">

## Any Questions?

Feel free to contact me on [LinkedIn](https://www.linkedin.com/in/junseo-yoo), or find out more about me at [typicaljeremy](http://typicaljeremy.com/)



This project is continuing to be made with ❤️ by Jeremy Yoo
