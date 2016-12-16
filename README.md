# Address Book

##Setup
 1. Clone the project
 2. Import into Android Studio
 3. Sync Gradle for dependencies
 4. Run

----------
##Background & Approach
The requirements are pretty straight forward, launch an app and display contacts. The requirement is also assuming there's a data pull from the [Random User service](randomuser.me) to populate this list.  Now there's a few things that aren't clear such as what fields are required and what's nice to have.  I looked at a good example of a address book -- in this case the built in Contacts app in Android.  While that app is more of a phone book, it has support for numerous fields such including things like address and birthdates so I felt it is still applicable.  

###Breakdown
First there needs to be an API call done to the service to get an initial list.  When the API returns data, the data would then be displayed as a list to the user.  Once the user has a scrollable list they should have ways to differentiate between their contacts.  In this scenario I've chosen to display the contact's photo, first name, and last name which should be sufficient for a high level view.  The user then has the option to click the contact to visit a new activity where they can see more information.  For the main contacts screen we use the Model-View-Presenter approach because there are a few different things that are happening.  This approach helps keep sections of the app separated based on UI, the manager for the UI and data, and the data sources themselves.  This approach is not used in the expanded details view because it is simply displaying data with two click interactions.

###Features Completed
 - API Call from Random User
 - Displayed list of contacts
 - Contact Photos
 - Ability to open the Dialer with the contact number on click
 - Ability to open Google Maps with the contact's address on click

###What I Would Like To Have Completed
-Proguard Configuration, estimate: 2 hours with testing rules
- More Unit Testing, specifically instrumentation on the UI navigation and more on the model creation/validation

###Features For Robustness
-SQlite for data storage after initial seed & user insertion/editing/deletion
-Search contacts to filter list


###Libraries used
-Retrofit (HTTP Requests & JSON Conversion)
- GSON (JSON Conversion)
- Glide (For image loading from URL)
- Support libraries (RecyclerView, CardView, etc)