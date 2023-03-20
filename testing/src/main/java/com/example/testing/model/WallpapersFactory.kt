package com.example.testing.model

import com.example.core.model.PhotoDomain
import com.example.core.model.SrcDomain

class WallpapersFactory {

    fun create(photo: Photo) =
        when (photo) {
            Photo.PhotoDomainSuccess -> photoDomain
        }

    sealed class Photo {
        object PhotoDomainSuccess : Photo()
    }

    private val photoDomain = PhotoDomain(
        description = "Free stock photo of busy, cbd, city",
        avgColor = "#464239",
        height = 6000,
        id = 15811217,
        liked = false,
        photographer = "Mateusz Walendzik",
        photographerId = 178202044,
        photographerUrl = "https://www.pexels.com/@mateusz",
        srcDomain = SrcDomain(
            landscape = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            large = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
            large2x = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            medium = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&h=350",
            original = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg",
            portrait = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
            small = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&h=130",
            tiny = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
        ),
        url = "https://www.pexels.com/photo/melting-light-city-road-15811217/",
        width = 4000
    )

}


