Sample Android App for showing pets
=============================

This is a sample Android App that showing Pets data.

## Building this App

 clone repo to android studio:

`git@github.com:ChetanKumbhar/SepiaApp.git`

## Running the Sample App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'


## Using the Sample App

* The first screen is a list of pets.
* Tapping on a pets list item brings up the second screen. This screen is a details screen showing the information related to the given pet
All Pets data is in <pets.josn>. 
* Also there is time restriction for using this application. Configuration for time is set in <config.json>. 
* We kept Both json files in assets locally and loading into application.

## tech stack used 
* Followed MVVM structure, Navigation components
* Glide, Gson , Kotlinx, liveData, hilt dagger, etc
* For test cases - used mockK


## project architecture

![WhatsApp Image 2022-12-28 at 22 39 07](https://user-images.githubusercontent.com/55182041/209893403-87bad235-ba91-4922-9f38-f2990598a1a6.jpeg)



