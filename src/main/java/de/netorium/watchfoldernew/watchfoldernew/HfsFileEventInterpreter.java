/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.netorium.watchfoldernew.watchfoldernew;


public class HfsFileEventInterpreter implements FileEventInterpreter {

    @Override
    public SpiedFileEvaluationResult evaluateSpiedFile(SpiedFile spiedFile) {
        return SpiedFileEvaluationResult.NOT_EVALUATED;
    }
    
}
