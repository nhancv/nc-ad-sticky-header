# Setup
[![](https://jitpack.io/v/nhancv/nc-android-sticky-header.svg)](https://jitpack.io/#nhancv/nc-android-sticky-header)

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Add the dependency

	dependencies {
	        compile 'com.github.nhancv:nc-android-sticky-header:v1.0'
	}

# Style Keep Header (default):
```
new StickyHeaderDecoration(this);
```
<img src="screenshots/stylekeepheader.png" width="300" height="533">


# Style NonKeep Header:
```
new StickyHeaderDecoration(this, false);
```
<img src="screenshots/stylenonkeepheader.png" width="300" height="533">
