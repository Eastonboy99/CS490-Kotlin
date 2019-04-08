package Homworks.HW3

@DslMarker
annotation class AddressBookDSL

data class AddressBook(val contacts: List<Person>)

data class Person(val firstName: String, val lastName: String, val mobile: String, val address: Address)

data class Address(val street: String, val city: String, val state: String, val zipCode: String){
    override fun toString(): String{
        return "$street, $city, $state, $zipCode"
    }
}






@AddressBookDSL
class AddressBuilder {
    var street = ""
    var city = ""
    var state = ""
    var zipCode = ""

    fun build() = Address(street, city, state, zipCode)

}

@AddressBookDSL
class PersonBuilder {
    var firstName: String = ""
    var lastName: String = ""
    var mobile: String = ""
    var address: Address = Address("", "", "", "")

    fun build() = Person(firstName, lastName, mobile, address)
}

@AddressBookDSL
class AddressBookBuilder {
    val contacts = mutableListOf<Person>()

    fun build() = AddressBook(contacts)

}

fun addressBook(init: AddressBookBuilder.() -> Unit): AddressBook {
    val builder = AddressBookBuilder()
    builder.init()
    return builder.build()
}

fun AddressBookBuilder.contact(init: PersonBuilder.() -> Unit) {
    val builder = PersonBuilder()
    builder.init()
    val person = builder.build()
    contacts.add(person)
}

fun PersonBuilder.address(init: AddressBuilder.() -> Unit) {
    val builder = AddressBuilder()
    builder.init()
    address = builder.build()

}

fun main(args: Array<String>) {
    val ab = addressBook {
        contact {
            firstName = "John"
            lastName =  "Doe"
            mobile = "123-123-1234"
            address {
                street = "11 Abc Ave"
                city = "Utica"
                state = "NY"
                zipCode = "13501"
            }
        }

        contact {
            firstName = "Jane"
            lastName =  "Doe"
            mobile = "123-123-1235"
            address {
                street = "12 Def Dr"
                city = "Utica"
                state = "NY"
                zipCode = "13502"
            }
        }
    }

    println("Your Address Book: ")
    ab.contacts.forEach{println("\t ${it.firstName}\t ${it.lastName}\t ${it.mobile}\t ${it.address}")}
}