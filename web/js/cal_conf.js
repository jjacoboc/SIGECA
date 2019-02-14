
//Define calendar(s): addCalendar ("Unique Calendar Name", "Window title", "Form element's name", Form name")
addCalendar("fecIniExoneracion", "Seleccionar Fecha", "fecIniExoneracion", "fichaIndividual");
addCalendar("fecVenExoneracion", "Seleccionar Fecha", "fecVenExoneracion", "fichaIndividual");
addCalendar("fecAdquisicion", "Seleccionar Fecha", "fecAdquisicion", "fichaIndividual");
addCalendar("fecInicio", "Seleccionar Fecha", "fecInicio", "fichaIndividual");
addCalendar("fecVencimiento", "Seleccionar Fecha", "fecVencimiento", "fichaIndividual");
addCalendar("fecConstruccion", "Seleccionar Fecha", "fecConstruccion", "fichaConstruccion");
addCalendar("fecObra", "Seleccionar Fecha", "fecConstruccion", "fichaObra");
addCalendar("fecDocumento", "Seleccionar Fecha", "fecha", "fichaDocumento");
addCalendar("fecEscritura", "Seleccionar Fecha", "fecEscPublica", "fichaIndividual");
addCalendar("fecInsPredio", "Seleccionar Fecha", "fecInsPredio", "fichaIndividual");
addCalendar("fecInsFabrica", "Seleccionar Fecha", "fecInsFabrica", "fichaIndividual");
addCalendar("fecFirDeclarante", "Seleccionar Fecha", "fecFirDeclarante", "fichaIndividual");
addCalendar("fecFirSupervisor", "Seleccionar Fecha", "fecFirSupervisor", "fichaIndividual");
addCalendar("fecFirTecCatastral", "Seleccionar Fecha", "fecFirTecCatastral", "fichaIndividual");
addCalendar("fecFirVerCatastral", "Seleccionar Fecha", "fecFirVerCatastral", "fichaIndividual");

addCalendar("fecAdqTitular", "Seleccionar Fecha", "fecAdqTitular", "fichaCotitularidad");
addCalendar("fecIniExoCotitular", "Seleccionar Fecha", "fecIniExoneracion", "fichaCotitularidad");
addCalendar("fecVenExoCotitular", "Seleccionar Fecha", "fecVenExoneracion", "fichaCotitularidad");
addCalendar("fecFirDecCotitularidad", "Seleccionar Fecha", "fecFirDeclarante", "fichaCotitularidad");
addCalendar("fecFirSupCotitularidad", "Seleccionar Fecha", "fecFirSupervisor", "fichaCotitularidad");
addCalendar("fecFirTecCatCotitularidad", "Seleccionar Fecha", "fecFirTecCatastral", "fichaCotitularidad");
addCalendar("fecFirVerCatCotitularidad", "Seleccionar Fecha", "fecFirVerCatastral", "fichaCotitularidad");
addCalendar("fecExpAnuncio", "Seleccionar Fecha", "fecExpedicion", "fichaAnuncio");
addCalendar("fecVenAnuncio", "Seleccionar Fecha", "fecVencimiento", "fichaAnuncio");

addCalendar("fecExpAutorizacion", "Seleccionar Fecha", "fecExpAutorizacion", "fichaActividadEconomica");
addCalendar("fecVenAutorizacion", "Seleccionar Fecha", "fecVenAutorizacion", "fichaActividadEconomica");
addCalendar("fecIniActividad", "Seleccionar Fecha", "fecIniActividad", "fichaActividadEconomica");
addCalendar("fecFirDecActividadEconomica", "Seleccionar Fecha", "fecFirDeclarante", "fichaActividadEconomica");
addCalendar("fecFirSupActividadEconomica", "Seleccionar Fecha", "fecFirSupervisor", "fichaActividadEconomica");
addCalendar("fecFirTecCatActividadEconomica", "Seleccionar Fecha", "fecFirTecCatastral", "fichaActividadEconomica");
addCalendar("fecFirVerCatActividadEconomica", "Seleccionar Fecha", "fecFirVerCatastral", "fichaActividadEconomica");

addCalendar("fecEscritura", "Seleccionar Fecha", "fecEscPublica", "fichaBienComun");
addCalendar("fecFirDecBienComun", "Seleccionar Fecha", "fecFirDeclarante", "fichaBienComun");
addCalendar("fecFirSupBienComun", "Seleccionar Fecha", "fecFirSupervisor", "fichaBienComun");
addCalendar("fecFirTecCatBienComun", "Seleccionar Fecha", "fecFirTecCatastral", "fichaBienComun");
addCalendar("fecFirVerCatBienComun", "Seleccionar Fecha", "fecFirVerCatastral", "fichaBienComun");

addCalendar("fecha_cese", "Seleccionar Fecha", "fecha_cese", "registroUsuario");

// default settings for English
// Uncomment desired lines and modify its values
// setFont("verdana", 9);
setWidth(50, 1, 15, 1);
// setColor("#cccccc", "#cccccc", "#ffffff", "#ffffff", "#333333", "#cccccc", "#333333");
// setFontColor("#333333", "#333333", "#333333", "#ffffff", "#333333");
// setFormat("yyyy/mm/dd");
setSize(180, 200, -200, 16);

// setWeekDay(0);
// setMonthNames("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Augosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
// setDayNames("Domingo", "Lunes", "Martes", "Mi�rcoles", "Jueves", "Viernes", "S�bado");
setLinkNames("[Cerrar]", "[Limpiar]");
