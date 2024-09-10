package com.example.beachapp.utility

import com.example.beachapp.model.Activity
import com.example.beachapp.model.Attraction

object ActivityAndAttraction {

    var BaseStorageUrl = "gs://beach-app-62ae8.appspot.com/";

    var attraction = mapOf(
        1 to Attraction(1, "Candolim Beach", "", "", "46_Candolim.jpg"),
        2 to Attraction(2, "Puri Beach", "", "", "134_Puri.jpg"),
        3 to Attraction(3, "Konark Beach", "", "", "131_Konark.jpg"),
        4 to Attraction(4, "Om Beach", "", "", "90_Om.jpg"),
        5 to Attraction(5, "Kovalam Beach", "", "", "109_Kovalam.jpg"),

        6 to Attraction(6, "Varkala Beach", "", "", "112_Varkala.jpg"),
        7 to Attraction(7, "NITK Beach", "", "", "84_NITK.jpg"),
        8 to Attraction(8, "Jalandhar Beach", "", "", "6_Jalandhar.jpg"),
        9 to Attraction(9, "Baga Beach", "", "", "45_Baga.jpg"),
        10 to Attraction(10, "Vagator Beach", "", "", "54_Vagator.jpg"),
        11 to Attraction(11, "Anjuna Beach", "", "", "49_Anjuna.jpg"),
        12 to Attraction(12, "Marina Beach", "", "", "156_Marina.jpg"),
        13 to Attraction(13, "Kanyakumari Beach", "", "", "166_Kanyakumari.jpg"),

        14 to Attraction(14, "Juhu Beach", "", "", "14_Juhu.jpg"),
        15 to Attraction(15, "Versova Beach", "", "", "16_Versova.jpg"),
        16 to Attraction(16, "Mandvi Beach", "", "", "10_Mandvi.jpg"),

        17 to Attraction(17, "Serenity Beach", "", "", "175_Serenity.jpg"),
        18 to Attraction(18, "Someshwar Beach", "", "", "92_Someshwar.jpg"),
        19 to Attraction(19, "Mahabalipuram Beach", "", "", "161_Mahabalipuram.jpg"),
        20 to Attraction(20, "Cherai Beach", "", "", "96_Cherai.jpg"),
    )


    var activities = mapOf(
        1 to Activity(1, "Scuba Diving", "Dive into an underwater paradise and explore vibrant marine life as you glide through crystal-clear waters on an unforgettable scuba diving adventure.", "9:00 AM - 5:00 PM", "46_ScubaDiving.jpg"),
        2 to Activity(2, "Para Sailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "7:00 AM - 6:00 PM", "46_ParaSailing.jpg"),
        3 to Activity(3, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "9:00 AM - 5:00 PM", "46_JetSki.jpg"),
        4 to Activity(3, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "7:00 AM - 6:00 PM","46_BananaBoat.jpg"),

        5 to Activity(5, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "9:00 AM - 5:00 PM", "134_Surfing.jpg"),
    6 to Activity(6, "Kayaking", "Glide serenely across crystal-clear waters and embrace the tranquility of nature as you navigate through stunning landscapes on an invigorating kayaking adventure.", "7:00 AM - 6:00 PM", "134_Kayaking.jpg"),
    7 to Activity(7, "Beach Volleyball", "Spike, serve, and dive into the sand as you revel in the high-energy excitement of a beach volleyball game, where every play is a chance for sun-soaked fun and teamwork.", "10:00 AM - 4:00 PM", "134_BeachVolley.jpg"),
    8 to Activity(8, "Horse rides", "Feel the wind in your hair and the rhythm of the gallop as you embark on a scenic horse riding adventure along sunlit trails and picturesque shores.", "11:00 AM - 5:00 PM", "134_HorseRiding.jpg"),

    9 to Activity(9, "ATV Bikes", "Conquer rugged trails and feel the adrenaline surge as you rev up an ATV bike, tackling adventurous terrains and embracing the thrill of off-road exploration.", "8:00 AM - 7:00 PM", "131_ATV.jpg"),
    10 to Activity(10, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "9:00 AM - 6:00 PM", "131_BananaBoat.jpg"),
    11 to Activity(11, "Parasailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "7:00 AM - 12:00 PM", "131_ParaSailing.jpg"),
    12 to Activity(12, "Speed boat", "Experience the ultimate thrill as you race across the open water, feeling the wind in your face and the excitement of high-speed waves on a power-packed speed boat adventure.", "6:00 AM - 2:00 PM", "131_SpeedBoat.jpg"),

        13 to Activity(13, "ATV ride", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "9:00 AM - 4:00 PM", "90_ATV.jpg"),
    14 to Activity(14, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "6:00 AM - 8:00 AM", "90_BananaBoat.jpg"),
    15 to Activity(15, "Dolphin Spotting", "Embark on a magical dolphin sighting tour, where the joy of spotting playful dolphins in their natural habitat creates an unforgettable beachside adventure brimming with wonder and excitement.", "5:00 PM - 7:00 PM", "90_Dolphin.jpg"),
    16 to Activity(16, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "9:00 AM - 3:00 PM", "90_Surfing.jpg"),

        17 to Activity(17, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "9:00 AM - 5:00 PM", "109_Surfing.jpg"),
    18 to Activity(18, "ParaGliding", "Experience the ultimate freedom as you glide high above the coastline while paragliding, where breathtaking aerial views and the thrill of soaring through the sky combine for an unforgettable beachside adventure.", "9:00 AM - 4:00 PM", "109_ParaGliding.jpg"),
    19 to Activity(19, "Kayaking", "Glide serenely across crystal-clear waters and embrace the tranquility of nature as you navigate through stunning landscapes on an invigorating kayaking adventure.", "8:00 AM - 3:00 PM", "109_Kayaking.jpg"),
    20 to Activity(20, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "7:00 AM - 5:00 PM", "109_JetSki.jpg"),

        21 to Activity(21, "Banana Boat","Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "7:00 PM - 10:00 PM", "112_BananaBoat.jpg"),
    22 to Activity(22, "Kayaking", "Glide serenely across crystal-clear waters and embrace the tranquility of nature as you navigate through stunning landscapes on an invigorating kayaking adventure.", "10:00 AM - 5:00 PM", "112_Kayaking.jpg"),
    23 to Activity(23, "ParaGliding", "Experience the ultimate freedom as you glide high above the coastline while paragliding, where breathtaking aerial views and the thrill of soaring through the sky combine for an unforgettable beachside adventure.", "6:00 PM - 10:00 PM", "112_ParaGliding.jpg"),
    24 to Activity(24, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "9:00 AM - 5:00 PM", "112_Surfing.jpg"),

        25 to Activity(25, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "5:00 PM - 9:00 AM", "84_BananaBoat.jpg"),
    26 to Activity(26, "Horse Riding", "Feel the wind in your hair and the rhythm of the gallop as you embark on a scenic horse riding adventure along sunlit trails and picturesque shores.", "8:00 AM - 3:00 PM", "84_HorseRiding.jpg"),
    27 to Activity(27, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "12:00 PM - 8:00 PM", "84_JetSki.jpg"),
    28 to Activity(28, "ParaSailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "9:00 AM - 4:00 PM", "84_ParaSailing.jpg"),

        29 to Activity(29, "Beach Volleyball", "Spike, serve, and dive into the sand as you revel in the high-energy excitement of a beach volleyball game, where every play is a chance for sun-soaked fun and teamwork.", "6:00 AM - 9:00 AM", "6_BeachVolley.jpg"),
    30 to Activity(30, "Horse Rides", "Feel the wind in your hair and the rhythm of the gallop as you embark on a scenic horse riding adventure along sunlit trails and picturesque shores.", "7:00 PM - 9:00 PM", "6_HorseRiding.jpg"),
    31 to Activity(31, "Kayaking", "Glide serenely across crystal-clear waters and embrace the tranquility of nature as you navigate through stunning landscapes on an invigorating kayaking adventure.", "7:00 AM - 9:00 AM", "6_Kayaking.jpg"),
    32 to Activity(32, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "10:00 AM - 2:00 PM", "6_Surfing.jpg"),

        33 to Activity(33, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "8:00 AM - 4:00 PM", "109_Surfing.jpg"),
    34 to Activity(34, "Kayaking", "Glide serenely across crystal-clear waters and embrace the tranquility of nature as you navigate through stunning landscapes on an invigorating kayaking adventure.", "9:00 AM - 5:00 PM", "112_Kayaking.jpg"),
    35 to Activity(35, "Beach Volleyball", "Spike, serve, and dive into the sand as you revel in the high-energy excitement of a beach volleyball game, where every play is a chance for sun-soaked fun and teamwork.", "8:00 AM - 2:00 PM", "6_BeachVolley.jpg"),
    36 to Activity(36, "Horse Rides", "Feel the wind in your hair and the rhythm of the gallop as you embark on a scenic horse riding adventure along sunlit trails and picturesque shores.", "10:00 AM - 5:00 PM", "49_HorseRiding.jpg"),

        37 to Activity(37, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "11:00 AM - 6:00 PM", "54_JetSki.jpg"),
    38 to Activity(38, "Parasailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "9:00 AM - 3:00 PM", "54_ParaSailing.jpg"),
    39 to Activity(39, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "7:00 AM - 6:00 PM", "54_BananaBoat.jpg"),
    40 to Activity(40, "Dolphin Sighting tours", "Embark on a magical dolphin sighting tour, where the joy of spotting playful dolphins in their natural habitat creates an unforgettable beachside adventure brimming with wonder and excitement.\n", "9:00 AM - 5:00 PM", "54_Dolphin.jpg"),

        41 to Activity(41, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "9:00 AM - 3:00 PM", "49_JetSki.jpg"),
    42 to Activity(42, "Parasailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "8:00 AM - 12:00 PM", "49_ParaSailing.jpg"),
    43 to Activity(43, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "10:00 AM - 4:00 PM", "49_BananaBoat.jpg"),
    44 to Activity(44, "Horse riding", "Feel the wind in your hair and the rhythm of the gallop as you embark on a scenic horse riding adventure along sunlit trails and picturesque shores.", "9:00 AM - 5:00 PM", "49_HorseRiding.jpg"),

        45 to Activity(45, "Scuba Diving", "Dive into an underwater paradise and explore vibrant marine life as you glide through crystal-clear waters on an unforgettable scuba diving adventure.", "7:00 PM - 11:00 PM", "156_Scuba Diving.jpg"),
    46 to Activity(46, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "9:00 AM - 4:00 PM", "156_BananaBoat.jpg"),
    47 to Activity(47, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "10:00 AM - 5:00 PM", "156_JetSki.jpg"),
    48 to Activity(48, "Snorkelling", "Dive into a vibrant underwater paradise with snorkeling, where you'll glide through crystal-clear waters and discover a mesmerizing world of colorful marine life just off the beach.", "5:30 AM - 7:30 AM", "156_Snorkelling.jpeg"),

        49 to Activity(49, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "9:00 AM - 3:00 PM", "166_Surfing.jpg"),
    50 to Activity(50, "Paddle Boat", "Unwind on the gentle waves as you glide across serene waters in a paddle boat, where the tranquil beach setting and rhythmic strokes create the perfect blend of relaxation and exploration.", "8:00 AM - 6:00 PM", "166_PaddleBoat.jpg"),
    51 to Activity(51, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "7:00 AM - 9:00 AM", "166_JetSki.jpg"),
    52 to Activity(52, "Horse Riding", "Feel the wind in your hair and the rhythm of the gallop as you embark on a scenic horse riding adventure along sunlit trails and picturesque shores.", "8:00 AM - 10:00 AM", "166_HorseRiding.jpg"),

        53 to Activity(53, "Zorbing", "Experience the ultimate thrill as you roll down the beach in a giant, transparent zorbing ball, where every exhilarating tumble and carefree spin turns the shoreline into your own adventure playground.", "9:00 AM - 1:00 PM", "14_Zorbing.jpeg"),
    54 to Activity(54, "Parasailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "8:00 AM - 4:00 PM", "14_ParaSailing.jpg"),
    55 to Activity(55, "Paddle Boat", "Unwind on the gentle waves as you glide across serene waters in a paddle boat, where the tranquil beach setting and rhythmic strokes create the perfect blend of relaxation and exploration.", "7:00 AM - 12:00 PM", "14_PaddleBoat.jpg"),
    56 to Activity(56, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "9:00 AM - 6:00 PM", "14_JetSki.jpg"),

        57 to Activity(57, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "6:00 AM - 8:00 PM", "16_BananaBoat.jpg"),
    58 to Activity(58, "Snorkelling", "Dive into a vibrant underwater paradise with snorkeling, where you'll glide through crystal-clear waters and discover a mesmerizing world of colorful marine life just off the beach.", "10:00 AM - 2:00 PM", "16_Snorkelling.jpeg"),
    59 to Activity(59, "Paddle Boat", "Unwind on the gentle waves as you glide across serene waters in a paddle boat, where the tranquil beach setting and rhythmic strokes create the perfect blend of relaxation and exploration.", "11:00 AM - 3:00 PM", "16_PaddleBoat.jpg"),
    60 to Activity(60, "Jet Ski", "Feel the rush of adrenaline as you carve through      sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "9:00 AM - 4:00 PM", "16_JetSki.jpg"),

        61 to Activity(61, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "7:00 AM - 9:00 AM", "10_BananaBoat.jpg"),
        62 to Activity(62, "Parasailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "10:00 AM - 12:00 PM", "10_ParaSailing.jpg"),
        63 to Activity(63, "Water Scooter", "Feel the thrill of the open sea as you zip across the crystal-clear waters on a water scooter, where every splash and breeze makes for an exhilarating beachside escape.", "1:00 PM - 4:00 PM", "10_WaterScooter.jpg"),
        64 to Activity(64, "Quad Biking", "Conquer the rugged trails and feel the rush of adrenaline as you embark on a quad biking adventure, where every twist and turn on the sandy terrain promises an unforgettable beachside thrill.", "8:00 AM - 5:00 PM", "10_QuadBike.jpg"),

        65 to Activity(65, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "9:30 AM - 11:30 AM", "175_BananaBoat.jpg"),
        66 to Activity(66, "Beach Volleyball", "Spike, serve, and dive into the sand as you revel in the high-energy excitement of a beach volleyball game, where every play is a chance for sun-soaked fun and teamwork.", "12:00 PM - 6:00 PM", "175_BeachVolley.jpg"),
        67 to Activity(67, "Kayaking", "Glide serenely across crystal-clear waters and embrace the tranquility of nature as you navigate through stunning landscapes on an invigorating kayaking adventure.", "7:00 AM - 2:00 PM", "175_Kayaking.jpg"),
        68 to Activity(68, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "11:00 AM - 4:00 PM", "175_Surfing.jpg"),

        69 to Activity(69, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "5:00 PM - 7:00 PM", "96_BananaBoat.jpg"),
        70 to Activity(70, "Parasailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "9:00 AM - 4:00 PM", "161_ParaSailing.jpg"),
        71 to Activity(71, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "6:30 PM - 8:00 PM", "161_JetSki.jpg"),
        72 to Activity(72, "Horse Riding", "Feel the wind in your hair and the rhythm of the gallop as you embark on a scenic horse riding adventure along sunlit trails and picturesque shores.", "8:00 AM - 6:00 PM", "92_HorseRiding.jpg"),

        73 to Activity(73, "Scuba Diving", "Dive into an underwater paradise and explore vibrant marine life as you glide through crystal-clear waters on an unforgettable scuba diving adventure.", "2:00 PM - 5:00 PM", "161_Scuba Diving.jpg"),
        74 to Activity(74, "Parasailing", "Experience the ultimate thrill as you soar high above the glistening ocean, taking in breathtaking views and the exhilarating freedom of parasailing on a perfect beach day.", "9:00 AM - 4:00 PM", "161_ParaSailing.jpg"),
        75 to Activity(75, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "6:00 AM - 7:00 PM", "161_JetSki.jpg"),
        76 to Activity(76, "Surfing", "Catch the perfect wave and ride the exhilarating rush of the ocean as you carve through the surf, feeling the sun on your face and the pure joy of surfing on an endless summer day.", "11:00 AM - 3:00 PM", "161_Surfing.jpg"),

        77 to Activity(77, "Catamaran Ride", "Set sail on a catamaran ride and glide across sparkling azure waters, where the gentle breeze and sunlit waves create a perfect harmony of relaxation and adventure right from the beach. ", "10:30 AM - 5:30 PM", "96_Catmaran.jpeg"),
        78 to Activity(78, "Kayaking", "Glide serenely across crystal-clear waters and embrace the tranquility of nature as you navigate through stunning landscapes on an invigorating kayaking adventure.", "12:00 PM - 4:00 PM", "175_Kayaking.jpg"),
        79 to Activity(79, "Jet Ski", "Feel the rush of adrenaline as you carve through sparkling waves and let the wind whip through your hair on a thrilling jet ski adventure at the sun-kissed beach.", "7:00 AM - 12:00 PM", "96_JetSki.jpg"),
        80 to Activity(80, "Banana Boat", "Hold on tight and feel the exhilaration as you bounce across the waves on a thrilling banana boat ride, laughing and splashing with friends under the sun.", "8:00 AM - 5:00 PM", "96_BananaBoat.jpg")

     )

}
















