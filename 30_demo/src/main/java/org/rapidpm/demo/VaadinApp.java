/**
 * Copyright © 2013 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rapidpm.demo;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.rapidpm.vaadin.component.pdfviewer.PdfViewer;

import java.io.InputStream;

@Route("")
@PWA(name = "PDFViewer for Flow", shortName = "PDFViewer for Flow")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class VaadinApp
    extends Composite<VerticalLayout> {

  //  private final PdfViewer       pdfViewer = new PdfViewer();
  private final VerticalLayout        pdfFiles = new VerticalLayout();
  private final MultiFileMemoryBuffer buffer   = new MultiFileMemoryBuffer();
  private final Upload                upload   = new Upload(buffer);

  public VaadinApp() {
    getContent().setHeight("100%");
//    pdfViewer.setHeight("100%");
    final Button btnUpload = new Button();
    btnUpload.addClickListener(e -> pdfFiles.removeAll());
    btnUpload.setText("start Uploading");
    upload.setUploadButton(btnUpload);
    upload.addSucceededListener(event -> {
      final InputStream    inputStream    = buffer.getInputStream(event.getFileName());
      final StreamResource streamResource = new StreamResource(event.getFileName(), () -> inputStream);

      final PdfViewer pdfViewer = new PdfViewer();
      pdfViewer.setStreamResource(streamResource);
      pdfViewer.setCard(false);
      pdfFiles.add(pdfViewer);
    });
    upload.setAcceptedFileTypes(".pdf");
    getContent().add(upload);
    getContent().add(pdfFiles);
  }
}
