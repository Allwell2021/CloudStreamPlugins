dependencies {
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}

// Use an integer for version numbers
version = 1

cloudstream {
    // A short description of what your plugin does
    description = "My custom multi-provider plugin for movies, TV series, and anime."
    
    // Your name or GitHub username
    authors = listOf("Allwell2021")

    /**
    * Status int as one of the following:
    * 0: Down
    * 1: Ok
    * 2: Slow
    * 3: Beta-only
    **/
    status = 1 // Will be 3 if unspecified

    // This tells CloudStream what kind of content to expect from this plugin
    // We added TvSeries and Anime here for your future stages.
    tvTypes = listOf("Movie", "TvSeries", "Anime")

    requiresResources = true
    language = "en"

    // You can leave this default icon for now, or change it later.
    iconUrl = "https://upload.wikimedia.org/wikipedia/commons/2/21/Kordiene_Logo.png"
}

android {
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}
