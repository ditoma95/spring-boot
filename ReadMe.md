INTEGRATION DE SWEET ALERT 2 DANS SPRING-BOOT

1. Dans la page layout.html (le fichier parent inclure)

```javascript
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
```

2. Dans le Controlleur (exemple RoleController)

```java
    @PostMapping("/create")
    public String saveRole(@ModelAttribute Role role, Model model) {
        Role recupNom = roleRepository.findByName(role.getName());
        if (recupNom.getName() == null) {
            roleRepository.save(role);

        }else{
            model.addAttribute("message", role.getName());
            return "pages/role/create";
        }
    
        System.out.println(recupNom.getName());
        return "redirect:/roles";
    }
```

Explication :

```
_Model modele : permet de retourne du message
_message : est la variable que j'ai defini pour renvoyer le message
_la methode de role.getName() : permet de retourner un attribut dynamaique sur la vue
```

3. Dans la vue de create.html

```html
<div th:if="${message}">
    <p id="recupidm" th:data-role="${message}"></p>
</div>
```

Explication :

```
_Je fais une condition sur la variable message qui contient le text a affichier
_Je cré un attribut id ="" pour me permet de pointer.
_data-[nom, prenom etc] : renvoyer l'attribut voulue
```

3. Ecrire le script js dans le layout.html

```js
<script>
    const recuperror = document.getElementById('recupidm');
    let role = recuperror.getAttribute("data-role")
    console.log(recuperror)
    if(recuperror != null){
        Swal.fire({
            title: 'Error!',
            text: 'le role ' + role + ' existe déjà',
            icon: 'error',
            confirmButtonText: 'Cool'
        })
    }
</script>
```

* [ ]  Pour démarrager le serveur

```java
mvn spring-boot:run
```

insert into users(email, enable, nom, password, prenom, telephone, username) values ('admin@gmail.com', true,'tamara', '$2a$10$b14yVKJs6PHf0oSuDX6JMe0us/m5XHCC0JCWGgoBQRNngeM2FTf7e', 'tamara', '6645654654', 'tamara')
