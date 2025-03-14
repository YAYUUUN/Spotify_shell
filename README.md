Especificación de la app HarmoniX

jajSound es una plataforma de música gratuita sin anuncios, diseñada para ofrecer una experiencia fluida y accesible. Permite a los usuarios escuchar y cachear canciones, con la particularidad de que los cacheamientos se eliminan automáticamente después de un tiempo para cumplir con regulaciones legales.
Al pulsar el botón de "Descargar", se ejecuta la función de descarga (cacheo) que has definido. En el ejemplo que te proporcioné, lo que ocurre es lo siguiente:
-	Se muestra un mensaje (Toast) indicando que se ha iniciado la descarga del video (por ejemplo, "Iniciando descarga de: [título del video]").
-	Se inicia una corrutina que simula la descarga mediante un retardo (delay) de x segundos.
-	Una vez transcurrido el tiempo, se muestra otro Toast diciendo "Descarga completada…".
Es decir, en este ejemplo se simula el proceso de descarga de audio para luego almacenarlo de forma temporal en caché, pero en realidad no se descarga ni se guarda de forma permanente ningún archivo.

Los usuarios pueden acceder como invitados, lo que les permite disfrutar de música en local, crear playlists y aprovechar todas las funcionalidades de la app. Sin tener que crear una cuenta. Sin embargo, si desean sincronizar su biblioteca y acceder desde múltiples dispositivos, pueden registrarse con su correo electrónico.

Actualmente, JajSound no tiene anuncios, aunque en el futuro podría incluir publicidad limitada durante el tiempo de espera en la descarga de canciones. Su enfoque principal es ofrecer una experiencia libre de interrupciones, con una interfaz intuitiva y un sistema de gestión de música eficiente, asegurando que los usuarios disfruten de su contenido sin restricciones innecesarias.

1. Reproducción de Música
• Un usuario invitado o registrado busca y reproduce una canción en la aplicación.
• Un usuario puede pausar, adelantar o retroceder una canción en la reproducción.
• Se permite la reproducción en segundo plano mientras usa otras aplicaciones.

2. Descarga de Canciones con Eliminación Automática
• Un usuario descarga una canción para escucharla sin conexión.
• La canción se elimina automáticamente después de un tiempo determinado.


3. Creación y Gestión de Playlists
• Un usuario crea una playlist con canciones favoritas.
• Puede añadir o eliminar canciones de su playlist.
• Puede reproducir una playlist en orden o en modo aleatorio.

4. Registro e Inicio de Sesión
• Un usuario se registra con su correo electrónico para acceder a su cuenta en múltiples dispositivos.
• Un usuario inicia sesión para sincronizar su biblioteca de música y playlists.
• Un usuario invitado puede registrarse en cualquier momento para guardar su historial y playlists.

5. Uso en Múltiples Dispositivos
• Un usuario registrado puede acceder a su cuenta desde otro dispositivo y recuperar su biblioteca.
• Sincronización de playlists y descargas entre dispositivos.

6. Búsqueda y Exploración de Música
• Un usuario puede buscar canciones, artistas o álbumes en la plataforma.
• Puede explorar listas de reproducción recomendadas y tendencias.

7. Publicidad Controlada (Futuro)
• Un usuario experimenta publicidad durante el tiempo de espera en la descarga de una canción.
• Se evalúa la posibilidad de un modelo de suscripción sin anuncios.


8. Configuración y Personalización
• Un usuario puede cambiar la calidad del audio en streaming y descargas.
• Puede gestionar sus preferencias de reproducción y borrar caché de canciones descargadas.


