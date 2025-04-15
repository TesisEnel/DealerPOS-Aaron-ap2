package org.aarondeveloper.dealerpos.di

import org.aarondeveloper.dealerpos.data.remote.remotedatasource.DealerDataSource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.EmailSendDataSource
import org.aarondeveloper.dealerpos.data.remote.remotedatasource.GeografiaDataSource
import org.aarondeveloper.dealerpos.data.repository.AdicionalesRepository
import org.aarondeveloper.dealerpos.data.repository.AjustesRepository
import org.aarondeveloper.dealerpos.data.repository.AuteticacionesRepository
import org.aarondeveloper.dealerpos.data.repository.CaracteristicasRepository
import org.aarondeveloper.dealerpos.data.repository.CarritoRepository
import org.aarondeveloper.dealerpos.data.repository.CategoriasRepository
import org.aarondeveloper.dealerpos.data.repository.CiudadesRepository
import org.aarondeveloper.dealerpos.data.repository.ConfiguracionRepository
import org.aarondeveloper.dealerpos.data.repository.DetalleCarritoAdicionalRepository
import org.aarondeveloper.dealerpos.data.repository.DetalleProductoSucursalesRepository
import org.aarondeveloper.dealerpos.data.repository.DetalleVentaPagosRepository
import org.aarondeveloper.dealerpos.data.repository.DetalleVentasRepository
import org.aarondeveloper.dealerpos.data.repository.EmailSendRepository
import org.aarondeveloper.dealerpos.data.repository.EstadosRepository
import org.aarondeveloper.dealerpos.data.repository.FavoritosRepository
import org.aarondeveloper.dealerpos.data.repository.IconosRepository
import org.aarondeveloper.dealerpos.data.repository.LenguajesRepository
import org.aarondeveloper.dealerpos.data.repository.PaisesRepository
import org.aarondeveloper.dealerpos.data.repository.ProductosRepository
import org.aarondeveloper.dealerpos.data.repository.ReclamacionesRepository
import org.aarondeveloper.dealerpos.data.repository.RolesRepository
import org.aarondeveloper.dealerpos.data.repository.SucursalesRepository
import org.aarondeveloper.dealerpos.data.repository.TarifasRepository
import org.aarondeveloper.dealerpos.data.repository.UsuariosRepository
import org.aarondeveloper.dealerpos.data.repository.VentasRepository
import org.aarondeveloper.dealerpos.data.repository.VerificacionesRepository
import org.aarondeveloper.dealerpos.presentation.screens.informacion.InformacionViewModel
import org.aarondeveloper.dealerpos.presentation.screens.introduccion.IntroViewModel
import org.aarondeveloper.dealerpos.presentation.screens.login.LoginViewModel
import org.aarondeveloper.dealerpos.presentation.screens.registrate.RegistrateViewModel
import org.aarondeveloper.dealerpos.presentation.screens.tienda.PrincipalViewModel
import org.aarondeveloper.dealerpos.presentation.screens.tienda.carrito.CarritoViewModel
import org.aarondeveloper.dealerpos.presentation.screens.tienda.favoritos.FavoritosViewModel
import org.aarondeveloper.dealerpos.presentation.screens.tienda.perfil.PerfilViewModel
import org.aarondeveloper.dealerpos.presentation.screens.verificacion.VerificacionesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("apiClient")) { provideKtorClient() }
    single(named("dealerPosApi")) { provideDealerPosApi(get(named("apiClient"))) }
    single(named("geografiaApi")) { provideGeograficaApi(get(named("apiClient"))) }
    single(named("emailsendApi")) { provideEmailSendApi(get(named("apiClient"))) }
}

val dataModule = module {
    factoryOf(::ProductosRepository)
    factoryOf(::CategoriasRepository)
    factoryOf(::FavoritosRepository)
    factoryOf(::AdicionalesRepository)
    factoryOf(::CaracteristicasRepository)
    factoryOf(::IconosRepository)
    factoryOf(::ReclamacionesRepository)
    factoryOf(::RolesRepository)
    factoryOf(::SucursalesRepository)
    factoryOf(::TarifasRepository)
    factoryOf(::VentasRepository)
    factoryOf(::DetalleVentasRepository)
    factoryOf(::DetalleVentaPagosRepository)
    factoryOf(::DetalleProductoSucursalesRepository)
    factoryOf(::CarritoRepository)
    factoryOf(::DetalleCarritoAdicionalRepository)
    factoryOf(::ConfiguracionRepository)
    factoryOf(::UsuariosRepository)
    factoryOf(::PaisesRepository)
    factoryOf(::EstadosRepository)
    factoryOf(::CiudadesRepository)
    factoryOf(::EmailSendRepository)
    factoryOf(::AjustesRepository)
    factoryOf(::VerificacionesRepository)
    factoryOf(::AuteticacionesRepository)
    factoryOf(::LenguajesRepository)
    factory<GeografiaDataSource> { GeografiaDataSource(get(named("geografiaApi"))) }
    factory<DealerDataSource> { DealerDataSource(get(named("dealerPosApi"))) }
    factory<EmailSendDataSource> { EmailSendDataSource(get(named("emailsendApi"))) }
}

val viewModelsModule = module {
    factoryOf(::IntroViewModel)
    factoryOf(::RegistrateViewModel)
    factoryOf(::VerificacionesViewModel)
    factoryOf(::LoginViewModel)
    factoryOf(::PrincipalViewModel)
    factoryOf(::PerfilViewModel)
    factoryOf(::InformacionViewModel)
    factoryOf(::FavoritosViewModel)
    factoryOf(::CarritoViewModel)
}

expect val nativeModule: Module