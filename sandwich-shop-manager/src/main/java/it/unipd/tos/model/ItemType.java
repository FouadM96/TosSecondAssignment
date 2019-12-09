////////////////////////////////////////////////////////////////////
// Fouad Mouad 1170480
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public enum ItemType {

		PANINI("Panini"), FRITTI("Fritti"), BEVANDE("Bevande");

		private final String textValue;

		ItemType(final String textValue) {
			this.textValue = textValue;
		}

		@Override
		public String toString() {
			return textValue;
		}
}
