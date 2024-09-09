
## Tasks Accomplished

- [x] **User Authentication & Authorization:**  Implemented user registration and login functionality using Firebase Authentication for _secure access_.
- [x] **Beach Information Storage:**  Used **Firebase** as a NoSQL database to store beach information and recreational activity data.
- [x] **Google Maps Integration:**  Integrated Google Maps API to visualize beach locations and their suitability.
- [x] **User Preferences:**  Implemented user registration and login functionality using Firebase Authentication for secure access.
- [x] **Data Visualizations:** Used **MPAndroidChart** to display weather and oceanic data in an _easy-to-read format_.
- [x] **User Authentication & Authorization:**  Implemented user registration and login functionality using Firebase Authentication for secure access.

 
## Technology Stack

This project leverages the following technologies:

- **[Kotlin](https://kotlinlang.org/):** A modern programming language used for Android app development, chosen for its concise syntax and seamless interoperability with Java.
  
- **[XML](https://developer.android.com/guide/topics/resources/layout-resource):** Used for designing the app's user interface, enabling a clear and structured approach to building layouts.

- **[Firebase](https://firebase.google.com/):** A NoSQL cloud database and backend service used for real-time data storage, and user authentication.

- **[Google Maps API](https://developers.google.com/maps):** Integrated for displaying beach locations and providing geospatial visualizations, enhancing user experience with interactive maps.

- **[MPAndroidChart](https://github.com/PhilJay/MPAndroidChart):** A powerful chart library for Android used to visualize beach conditions such as oceanic alerts, wave height, and weather data, providing easy-to-read insights for users.
  
## Key Features

- **User Authentication & Preferences Management:** Users can sign in, manage preferences, and bookmark beaches for quick access.
- **Real-Time Notifications:** Alerts users to safety concerns (e.g., storms, currents) based on their location.
- **Interactive Maps:** Google Maps integration helps users visualize beaches and nearby accommodation options.
- **Data Visualization**: Uses charts to show current beach conditions like wave heights, wave direction, tidal elevation and other coastal parameters.
- **Bookmarking Beaches:**  Users can bookmark their favorite beaches and activities for future reference.
    
## Local Setup Instructions (Write for both windows and macos)

Follow these steps to set up the project on Android Studio.

**Prerequisites**

**JDK**: Ensure you have Java Development Kit (JDK 8+) installed. You can download the JDK from the official site: JDK Downloads
**Android Studio**: Download and install the latest version of Android Studio from: Android Studio Downloads

1. **Clone the Repository**

    On both Windows and macOS, start by cloning the repository:

   ```bash
   git clone https://github.com/nitingoyal123/SIH_BITWISE-INNOVATORS
   cd SIH_BITWISE-INNOVATORS/code
   ```
2. Install Android Studio:
   Run the installer and follow the steps to install Android Studio.
   During installation, choose the default options for SDK and virtual devices.

3. **Open in Android Studio**

 - Open Android Studio and select Open an existing project.
 - Navigate to the folder where you cloned the project.
   
4. **Set Up Firebase**

 -  Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project.
 -  Enable Authentication, Firestore Database and Realtime Database.  

5. **Run the App**

   Click on the **Run button** or use **Shift + F10** to build and run the app on a _connected device or emulator_.
  
