package util;

public abstract class PaginacionHelper {
	private int nrpag; // numero de registros a mostrar por pagina.
	private int pagina; // numero de pagina.
	public PaginacionHelper() {
		
	}
	public PaginacionHelper(int tampagina, int pag) {
		this.nrpag = tampagina;
		this.pagina = pag;
	}

	// Método abstracto que cuenta los elementos a mostrar
	// será implementada cuando instanciemos un objeto
	// PaginacionHelper.
	public abstract long getItemsCount();

	public int getNrpag() {
		return nrpag;
	}

	public void setNrpag(int nrpag) {
		this.nrpag = nrpag;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public int getPrimerElementoPagina() {
		return (pagina * nrpag) + 1;
	}

	public long getUltimoElementoPagina() {
		long i = getPrimerElementoPagina() + nrpag - 1;
		long count = getItemsCount();
		if (i > count) {
			i = count;
		}
		if (i < 1) {
			i = 1;
		}
		return i;
	}

	public boolean isHayPaginaSiguiente() {
		return (((pagina + 1) * nrpag) + 1) <= getItemsCount();
	}

	public void getPaginaSiguiente() {
		if (isHayPaginaSiguiente()) {
			pagina++;
		}
	}

	public boolean isHayPaginaAnterior() {
		return pagina > 0;
	}

	public void getPaginaAnterior() {
		if (isHayPaginaAnterior()) {
			pagina--;
		}
	}

}

