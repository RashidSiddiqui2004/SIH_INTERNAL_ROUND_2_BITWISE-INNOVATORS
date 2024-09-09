
## Tasks Accomplished

- [x] **User Authentication & Authorization:**  Implemented user registration and login functionality with Firebase Authentication to securely manage user access to the sheets.
- [x] **Collaborative Sheet Editing:** Enabled real-time collaborative editing of sheets, allowing multiple users to work on the same sheet simultaneously with conflict resolution mechanisms.
- [x] **Email Notifications:** Integrated email notification system to inform users when they are added as collaborators to a sheet.


## Technology Stack

This project leverages the following technologies:

- **[Next.js](https://nextjs.org/):** A React framework that allows for server-side rendering and static site generation, chosen for its performance benefits and ability to create optimized, SEO-friendly pages.
- **[Firebase](https://firebase.google.com/):** Used for authentication, real-time database, and hosting due to its scalability.
- **[Tailwind CSS](https://tailwindcss.com/):** A utility-first CSS framework that allowed us to quickly style the application with a consistent and responsive design.
- **[Node.js](https://nodejs.org/en):** Powers the backend API, handling tasks such as sending email notifications and managing server-side logic.
  
## Key Features

- **Add Collaborators:** Users can add new collaborators to specific sheets, enabling teamwork and shared access to important data.
- **Search Functionality:** Allows users to quickly search and filter through their sheets, making it easier to locate specific documents.
- **Real-time Collaboration:** Users can collaboratively edit sheets in real-time, with updates reflected immediately for all collaborators.
- **Email Invitations:** Automatically sends an email invitation to users added as collaborators, with a direct link to the sheet.
- **Context Menu Options:** Provides a custom context menu for quick access to common actions like saving the sheet and adding collaborators.

## Local Setup Instructions (Write for both windows and macos)

Follow these steps to run the project locally

1. **Clone the Repository**

    On both Windows and macOS, start by cloning the repository:

   ```bash
   git clone https://github.com/nitingoyal123/SIH_INTERNAL_ROUND_1_BITWISE-INNOVATORS
   cd SIH_INTERNAL_ROUND_1_BITWISE-INNOVATORS/code
   ```
2. **Install Dependencies**

   On both Windows and macOS, install the project dependencies:

   ```bash
   npm install
   # or
   yarn install
   ```
   
3. **Set Up Firebase**

 -  Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project.
 -  Enable Authentication, Firestore Database and Realtime Database.  

4. **Set Up Environment Variables**

   Create a .env.local file in the root directory of your project. Copy the content from .env.example and replace with your credentials.

4. **Run the Development Server**

### Windows:
    
   Run the development server using the following command:

```bash
npm run dev
# or
yarn dev
```

### macOS:
   
   The command to run the development server is the same:

```bash
npm run dev
# or
yarn dev
```

5. The website will be running at [http://localhost:3000](http://localhost:3000) .