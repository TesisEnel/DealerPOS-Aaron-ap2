package org.aarondeveloper.dealerpos.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.aarondeveloper.dealerpos.presentation.screens.informacion.InformacionScreen
import org.aarondeveloper.dealerpos.presentation.screens.introduccion.IntroScreen
import org.aarondeveloper.dealerpos.presentation.screens.login.LoginScreen
import org.aarondeveloper.dealerpos.presentation.screens.recuperacion.RecuperacionEmailScreen
import org.aarondeveloper.dealerpos.presentation.screens.registrate.RegistrateScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.PrincipalScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito.CarritoComprasScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito.CompletadaCompraScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito.FormularioCompraScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito.PagoCompraScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito.ResumenCompraScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.favoritos.FavoritosScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.herramientas.HerramientasScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.home.HomeScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.home.detalles.DetallesProductoScreen
import org.aarondeveloper.dealerpos.presentation.screens.tienda.perfil.PerfilScreen
import org.aarondeveloper.dealerpos.presentation.screens.verificacion.CompletadoScreen
import org.aarondeveloper.dealerpos.presentation.screens.verificacion.VerificacionScreen


@Composable
fun NavigationNavHost(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.IntroScreen) {

        // Introduccion
        composable<Screen.IntroScreen> {
            IntroScreen(
                onAutenticado = {
                    navHostController.navigate(Screen.PrincipalScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onIniciarSesionClick = {
                    navHostController.navigate(Screen.LoginScreen)
                },
                onRegistrateClick = {
                    navHostController.navigate(Screen.RegistrateScreen)
                }
            )
        }

        // Login
        composable<Screen.LoginScreen> {
            LoginScreen(
                onAutenticado = {
                    navHostController.navigate(Screen.PrincipalScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onVerificarse = {
                    navHostController.navigate(Screen.VerificacionScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNavOlvidasteContrasenaClick = {
                    navHostController.navigate(Screen.RecuperacionEmailScreen)
                }
            )
        }


        // Registrate
        composable<Screen.RegistrateScreen> {
            RegistrateScreen(
                onNavBackClick = {
                    navHostController.popBackStack()
                },
                onNavConfirmarClick = {
                    navHostController.navigate(Screen.VerificacionScreen)
                }
            )
        }

        // Recuperacion

            // Recuperacion - RecuperacionEmailScreen
            composable<Screen.RecuperacionEmailScreen> {
                RecuperacionEmailScreen(
                    onContinuarClick = {
                        navHostController.navigate("")
                    }
                )
            }



        // Verificacion

            // Verificacion - Codigo
            composable<Screen.VerificacionScreen> {
                VerificacionScreen(
                    onNavContinuarClick = {
                        navHostController.navigate(Screen.CompletadoScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }

            // Verificacion - Completado
            composable<Screen.CompletadoScreen> {
                CompletadoScreen (
                    onFinalizarClick = {
                        navHostController.navigate(Screen.LoginScreen){
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }

                    }
                )
            }

        // Clientes

            // Clientes - PrincipalScreen
            composable<Screen.PrincipalScreen> {
                PrincipalScreen(
                    onNoAutenticado = {
                        navHostController.navigate(Screen.IntroScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onFavoritosClick = {
                        navHostController.navigate(Screen.FavoritosScreen)
                    },
                    onHerramientasClick = {
                        navHostController.navigate(Screen.HerramientasScreen)
                    },
                    onHomeClick = {
                        navHostController.navigate(Screen.PrincipalScreen) {
                            popUpTo(Screen.PrincipalScreen) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onCarritoClick = {
                        navHostController.navigate(Screen.CarritoScreen)
                    },
                    onPerfilClick = {
                        navHostController.navigate(Screen.PerfilScreen)
                    },
                    onDetalleProducto = {
                        navHostController.navigate(Screen.DetallesProductoScreen)
                    }
                )
            }

            // Clientes - Favoritos
            composable<Screen.FavoritosScreen> {
                FavoritosScreen(
                    onNoAutenticado = {
                        navHostController.navigate(Screen.IntroScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onNavBackClick = {
                        navHostController.popBackStack()
                    },
                    onInformacion = {
                        navHostController.navigate(Screen.InformacionScreen)
                    }
                )
            }

            // Clientes - Herramientas
            composable<Screen.HerramientasScreen> {
                HerramientasScreen(

                )
            }

            // Clientes - Inicio
            composable<Screen.HomeScreen> {
                HomeScreen(
                    onDetalleProducto = {
                        navHostController.navigate(Screen.DetallesProductoScreen)
                    }
                )
            }

                // Clientes - Inicio - Detalles Producto
                composable<Screen.DetallesProductoScreen> {
                    DetallesProductoScreen(
                        onNavBackClick = {
                            navHostController.popBackStack()
                        }
                    )
                }

            // Clientes - Carrito
            composable<Screen.CarritoScreen> {
                CarritoComprasScreen(
                    onNoAutenticado = {
                        navHostController.navigate(Screen.IntroScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onNavBackClick = {
                        navHostController.popBackStack()
                    },
                    onInformacion = {
                        navHostController.navigate(Screen.InformacionScreen)
                    },
                    onFormularioCompra = {
                        navHostController.navigate(Screen.FormularioCompraScreen)
                    }
                )
            }

            // Clientes - Formulario de Compra
            composable<Screen.FormularioCompraScreen> {
                FormularioCompraScreen(
                    onNoAutenticado = {
                        navHostController.navigate(Screen.IntroScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onNavBackClick = {
                        navHostController.popBackStack()
                    },
                    onInformacion = {
                        navHostController.navigate(Screen.InformacionScreen)
                    },
                    onPagoCompra = {
                        navHostController.navigate(Screen.PagoCompraScreen)
                    }
                )
            }

            // Clientes - PagoCompra
            composable<Screen.PagoCompraScreen> {
                PagoCompraScreen(
                    onNoAutenticado = {
                        navHostController.navigate(Screen.IntroScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onNavBackClick = {
                        navHostController.popBackStack()
                    },
                    onInformacion = {
                        navHostController.navigate(Screen.InformacionScreen)
                    },
                    onResumenCompra = {
                        navHostController.navigate(Screen.ResumenCompraScreen)
                    }
                )
            }

            // Clientes - Resumen Compra
            composable<Screen.ResumenCompraScreen> {
                ResumenCompraScreen(
                    onNoAutenticado = {
                        navHostController.navigate(Screen.IntroScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onNavBackClick = {
                        navHostController.popBackStack()
                    },
                    onInformacion = {
                        navHostController.navigate(Screen.InformacionScreen)
                    },
                    onCompletadoCompra = {
                        navHostController.navigate(Screen.CompletadaCompraScreen)
                    }
                )
            }

            // Clientes - Completada Compra
            composable<Screen.CompletadaCompraScreen> {
                CompletadaCompraScreen(
                    onNoAutenticado = {
                        navHostController.navigate(Screen.IntroScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onFinalizadoCompra = {
                        navHostController.navigate(Screen.PrincipalScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }

            // Clientes - Perfil
            composable<Screen.PerfilScreen> {
                PerfilScreen(
                    onNoAutenticado = {
                        navHostController.navigate(Screen.IntroScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onNoPrincipal = {
                        navHostController.navigate(Screen.PrincipalScreen) {
                            popUpTo(Screen.PerfilScreen) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onNavBackClick = {
                        navHostController.popBackStack()
                    },
                    onInformacion = {
                        navHostController.navigate(Screen.InformacionScreen)
                    }
                )
            }


        // Informacion
        composable<Screen.InformacionScreen> {
            InformacionScreen(
                onNavBackClick = {
                    navHostController.popBackStack()
                },
            )
        }
    }
}

sealed class Screen {

    // Introduccion
    @Serializable
    data object IntroScreen : Screen()


    // Login
    @Serializable
    data object LoginScreen : Screen()

    // Registrarte
    @Serializable
    data object RegistrateScreen : Screen()

    // Verificacion
    @Serializable
    data object VerificacionScreen : Screen()
    @Serializable
    data object CompletadoScreen : Screen()

    // Tienda
    @Serializable
    data object PrincipalScreen : Screen()

        // Home
        @Serializable
        data object HomeScreen : Screen()

            // Home - DetallesProductos
            @Serializable
            data object DetallesProductoScreen: Screen()

        // Carrito
        @Serializable
        data object CarritoScreen : Screen()
        @Serializable
        data object FormularioCompraScreen : Screen()
        @Serializable
        data object ResumenCompraScreen : Screen()
        @Serializable
        data object PagoCompraScreen : Screen()
        @Serializable
        data object CompletadaCompraScreen : Screen()


        // Perfil
        @Serializable
        data object PerfilScreen : Screen()

        // Favoritos
        @Serializable
        data object FavoritosScreen : Screen()

        // Herramientas
        @Serializable
        data object HerramientasScreen : Screen()

            // Compras


            // Develuciones


            // Pagos


            // Reclamaciones


            // Sucursales



    // Recuperacion
    @Serializable
    data object RecuperacionEmailScreen : Screen()
    @Serializable
    data object RecuperacionCodigoScreen : Screen()
    @Serializable
    data object RecuperacionContrasenaScreen : Screen()
    @Serializable
    data object RecuperacionCompletadoScreen : Screen()


    // Informacion
    @Serializable
    data object InformacionScreen : Screen()

    // Administracion

}
