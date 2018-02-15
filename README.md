#**Journaler**
#####Basic Android App to Learn Kotlin

##_Actions-To-Date_

1) Create a new project (with NO activity)
2) Update 'root' level '**.gitignore**' file from book
3) Update 'app' level '**.gitignore**' file from book
4) Update 'root' level '**build.gradle**' file from book
5) Update 'app' level '**build.gradle**' file from book. (Overwrite ALL the contents)
6) Update dependencies that require updating
7) GOTO: VCS and click 'Share Project on GitHub' 
8) Log into GitHub and confirm the project has been uploaded 
9) Update the 'app' level '**build.gradle**' file from book.(Used for 'build types' & 'flavors')  
10) Confirm it worked by checking in the 'Build Variant' window
11) Create a new 'class' file in app/src/main/kotlin/com/journaler/'**Journaler.kt**' - copy contents from book
12) Open 'app' level '**AndroidManifest.xml**' and update from book
13) Create the new 'Application' class in /main/kotlin/com/journaler/'Journaler.kt' class file (and update from book)
14) Create the new 'Main' application class in /main/java/journaler/activity/'**MainActivity.kt**' class file 
15) Create a new 'empty' activity at the 'main' level (This should create your '**activity_main.xml**' in the res folder) )
16) Amend '**activity_main.xml**' to use a LinearLayout
17) '**Journaler.kt**' - defining our own log messages
18) Checking the 'run' window for created log messages
19) Using the gradle build tool and its commands (clean, assemble, assembleDebug, assembleCompletedDebug)
20) Debug your application - following breakpoints - Attaching the debugger

'***Chapter 3 - Screens***' 
1) Download and install the 'Pencil' app (for GUI layouts & screens)
2) Add in 'implementation 'com.android.support:appcompat-v7:26.1.0'
3) Moving the tag field from 'MainActivity' to 'BaseActivity'
4) Amend 'MainActivity' by removing all the 'log' functions and place them into 'BaseActivity.kt'
5) Create '**Item**, **Note** and **Todo** Activity' class files and place them into the 'activity' folder
6) 'Item' extends our 'Base' class, 'Todo' & 'Note' extend the 'ItemActivity' class
7) Create '**activity_note.xml**' & '**activity_todo.xml**' in the activities folder
8) Update 'note', 'item' & 'todo' classes
9) Register our screens (activities) in 'view groups' within the '**manifest.xml**'
10) Create and update a new '**dimens.xml**' file in the values folder
11) Create '**activity_header.xml**' layout file that will be included on all screens
12) Add 2 'buttons' and a 'textView' to the '**activity_header.xml**'
13) Add in a 'ListView' and a 'FloatingActionButton' in the '**activity_main.xml**'
14) Update 'Base',''Main' & 'Item' Activity with override functions to get the activity titles (RUN)
15) Update 'activity_note.xml' file with a ScrollView and 2 editTextViews
16) Update '**strings.xml**'
17) Update '**activity_todo.xml**' with a 'ScrollView' and other UI components
18) Create a new package 'fragment' & 'BaseFragment' class inside it
19) Create a new '**fragment_items.xml**' and update accordingly  
20) Adding in our fragment container details
21) Create '**fragment_manual.xml**'(layout) and populate
22) Create '**ManualFragment.kt**' class and populate accordingly
23) Adding in 'fragment back stack' by updating the onCreate() method within '**MainActivity.kt**'(RUN)
24) Replace the 'FrameLayout' with the 'ViewPager' view within '**MAinActivity.kt**' class (RUN)


   '***Chapter 4 - Connecting Screen Flow***'
1) ====== Creating and application bar ==============================
2) ====== Using drawer navigation ===================================
3) ====== Android Intents ===========================================
4) ====== Passing Information between activities and fragments ======
- Creating an application bar requires replacing the 'FragmentActivity' with 'AppCompatActivity'
- Update a new theme in the '**AndroidManifest.xml**' file with "Theme.AppCompat.Light.NoActionBar"
- Update '**activity_main.xml**' to add in an action 'toolbar' 
- '**activity_main**' - Remove the 'header' and add a 'toolbar' widget on ALL layouts
- '**BaseActivity**' - Update to use the new toolbar
- Create a 'layout\menu\main.xml' file populate accordingly
- '**BaseActivity**' - need to add a menu to the app bar. 
- NOTE: Here we switched our header to the application bar
- '**activity_main**' - Add in the 'DrawerLayout' view into our layout
- '**adapter_navigation_drawer**' - We now have an empty navigation drawer and we 
have to populate it with some buttons. Create a new layout for each navigation item 
- Create '**\navigation\NavigationDrawerItem.kt**' data class. Populate it accordingly
- '**MainActivity**' - assigning the above adapter here. We hav instantiated several 'NavigationDrawerItemInstances'
instances. Assigned titles to the buttons, and instigated runnable actions that will execute. 
Each runnable wil l jump to a specific page of our viewPager. We passed all instances to the adapter
as one single mutable list.
- Next we start to connect the activities (Todo & Notes) so when our user clicks on the button
the proper screen will open.
- Create "**\model\MODE.kt** enum class" and populate its code
- Update '**ItemActivity**' 
- Update '**ItemsFragment**' - Here we trigger the activity to open. Now, you click on the 
FAB button and it opens a popup menu which sends you to either the 'Todo' or 'Notes' screen
'**itemsFragment**' - passing information between activities and fragments
'**itemActivity**' - Update as per book 
'**TodoActivity**' - associated values to 'date/time' picker buttons 
**SUMMARY**
- We have now connected our interface and established a real application flow.
- Established connections between screens by setting proper actions to UI elements
- The data is now going from point to point 


   '***Chapter 5 - Look & Feel***'
1) ====== Themes and Styles in Android ==============================
2) ====== Working with Assets =======================================
3) ====== Custom Fonts & Coloring ===================================
4) ====== Animation & animation sets ================================
- Update '**styles.xml**
- Update '**colors.xml**' 
- Update '**AndroidManifest.xml**'  
- Update '**fragment_items.xml**' (RUN) (Branded application in orange)
- Update '**colors.xml**' ()with a new color palette)
- Update '**styles.xml**'   
- Create selector '**selector_button_green.xml**' in the res\drawable folder
- Set buttons style within '**activity_todo.xml**' (RUN) 
- Add styles for input fields and navigation drawers to your '**styles.xml**'
- Create selector '**selector_button_grey.xml**' in the res\drawable folder (RUN)
- Extend '**BaseActivity**' to handle fonts (pg 144)
- Create '**tags.xml**' within the res\values folder for setting tags
- Update '**styles.xml**' with new style (for simple_button) (RUN)
- Update '**TodoActivity**' for a new button style
- Update '**dimens.xml**' to define the radius for a button with rounded corners
- Update '**colors.xml**' with second color for gradients
- Update '**styles.xml**' with the additional styles for green buttons
- Create '**rect_rounded_green.xml**' rectangular rounded drawable (in drawable folder)
- Create '**rect_rounded_green_dark.xml**' rectangular rounded drawable (in drawable folder)
- Create '**rect_rounded_grey_disabled.xml**' rectangular rounded drawable (in drawable folder)
- Update '**selector_button_green**' with the drawable corner radius values. (RUN)
- Start to add in animations
- Create opening animations '**fade_in.xml**'/'**fade_out.xml**'/'**bottom_to_top.xml**'/'**top_to_bottom.xml**'
 '**hide_to_top.xml**'/'**hide_to_bottom.xml**' within the res/anim folder 
- Update '**activity_main.xml**' add the background for Toolbar parent view
- Update '**activity_note.xml**' & '**activity_todo.xml**', nest the toolbar in one 
more parent so the final color is the same as the color for the title field below 
the toolbar.
- Update '**BaseActivity.kt**' to apply our animations by applying the 
 'overridePendingTransition' method (takes 'enter' and 'exit' animations as params).
 Also update the 'onResume' & 'onPause' methods as well 
- Create '**animations.kt**' in the com.journaler\extension folder and its function 
- Update '**itemsFragment.kt**' using view attributes and animation sets.  
- Add in '**add.png**' to res\drawable
- Update '**fragment_items.xml**' to add in the plus sign 

   '***Chapter 6 - Permissions***'
1) ====== Permissions form the Android Manifest =====================
2) ====== Requesting Permissions ====================================
3) ====== Permission Handling the Kotlin Way ========================
- Update '**AndroidManifest.xml**' with the permissions we need for our app
- Create a package called 'permission' (within com.journaler)
- Create TWO files here '**PermissionCompatActivity.kt**' & '**PermissionRequestCallback.kt**'
- Update each file with its relevant code
- Update '**BaseActivity.kt**' with a requestPermission() call
 
   '***Chapter 7 - Working With Databases***'
1) ====== Introduction To SQLite ====================================
2) ====== Describing Database =======================================
3) ====== CRUD Operations ===========================================
- Create a 'database' package
- Inside the 'model' package create Entry, Note & Todo classes 
    (Note, Todo will extend entry, which extends the DbModel class)
- Now we describe our DB by defining ada DB helper class responsible for
  DB initialisation.
- Populate the Entry, Note & Todo classes accordingly.
- We added two fields:
    1 - this contains the current 'Note' instance we are editing 
    2 - the other holds info about the currents users location
- Defined a 'TextWatcher' (this is a listener we will assign our EditText views) 
instance, on each change the proper update method will be triggered.
- That method wil create a new 'Note' class and persist it into the database if it exists or not.
- Defined our 'LocationListener' to put received location into the 'location' field 
and to then un subscribe itself. 
- We then get the current value for a 'Note' title and its main content and create a 
new 'Note' instance
- We start to use AsyncTasks (due to DB operations) 
- 'AsyncTask' class uses 'input', 'progress' & 'result type' params. (input = Note, 
progress = do not have one, result = boolean (success or not))
- NOTE: Main work is done by 'doInBackground' while result is handled in 'onPostExecute'
- Assign 'textWatcher' to 'EditText' views in the onCreate() method
- Defined (most important method) 'updateNote()'

   '***Chapter 8 - Android Preferences***'
1) ====== What are Preferences and how can you use them =============
2) ====== Defining your own Preferences manager =====================
- Create the following files within the 'com.journaler\preferences' directory
    - PreferencesConfiguration
    - PreferencesProvider
    - PreferencesProviderAbstract

   '***Chapter 9 - Concurrency in Android***'
1) ====== Handlers & Threads ========================================
2) ====== AsyncTask =================================================
2) ====== Android Looper ============================================
2) ====== Delayed Execution =========================================

   '***Chapter 10 - Android Services***'
1) ====== Service Categorisation ====================================
2) ====== Basics of Android Services ================================
2) ====== Defining the Main Application Service  ====================
2) ====== Defining the Intent Service ===============================

   '***Chapter 11 - Messaging***'
1) ====== Android Broadcasts ========================================
2) ====== Listening for Broadcasts ==================================
2) ====== Creating Broadcasts =======================================
2) ====== Listening for Network Events ==============================

    
 

 


