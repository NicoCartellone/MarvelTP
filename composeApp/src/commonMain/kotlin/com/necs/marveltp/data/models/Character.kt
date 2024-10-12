package com.necs.marveltp.data.models

data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
)

val characters = (1..100).map {
    Character(
        id = it.toLong(),
        name = "Character $it",
        description = "Description for character $it",
        thumbnailUrl = "https://picsum.photos/200/300?random=$it",
    )
}

//val characters = listOf(
//    Character(
//        id = 1,
//        name = "Spider-Man",
//        description = "Un joven con poderes arácnidos que lucha contra el crimen en Nueva York.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 2,
//        name = "Wonder Woman",
//        description = "Una guerrera amazona con habilidades sobrehumanas y un fuerte sentido de la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 3,
//        name = "Batman",
//        description = "Un vigilante oscuro que utiliza su inteligencia y recursos para luchar contra el crimen en Gotham.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 4,
//        name = "Iron Man",
//        description = "Un genio industrial que crea una armadura avanzada para proteger el mundo.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 5,
//        name = "Superman",
//        description = "Un alienígena con poderes increíbles que defiende la verdad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 6,
//        name = "Thor",
//        description = "El dios del trueno que empuña un martillo mágico y defiende Asgard y la Tierra.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 7,
//        name = "Black Widow",
//        description = "Una experta en espionaje y combate, miembro de los Vengadores.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 8,
//        name = "Hulk",
//        description = "Un científico que se transforma en un gigante verde con fuerza descomunal cuando se enfada.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 9,
//        name = "Flash",
//        description = "El hombre más rápido del mundo, capaz de viajar a velocidades increíbles.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 10,
//        name = "Green Lantern",
//        description = "Un miembro de una fuerza policial intergaláctica que utiliza un anillo con poder ilimitado.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 12,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 14,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 15,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ), Character(
//        id = 16,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 17,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 18,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 19,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 20,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//    Character(
//        id = 21,
//        name = "Captain America",
//        description = "Un super soldado con un escudo indestructible que lucha por la libertad y la justicia.",
//        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcvYyJiTttohvLFEiQU2YzED5yf8VmurdYsmzJyIFFw8uafG0vjtfEmYpK9bA6tf_rFZg&usqp=CAU",
//    ),
//)