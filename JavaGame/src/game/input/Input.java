package game.input;

import graphics.Renderer;

/*
    interface for receving input
*/
public interface Input
{
    default boolean keyDown(int key)
    {
        return InputHandler.keyDown(key);
    }

    default boolean keyPressed(int key) 
    {
        return InputHandler.keyPress(key);
    }

    default boolean keyReleased(int key) 
    {
        return InputHandler.keyUp(key);
    }

    default boolean mousePressed(MouseButtons button)
    {
        return InputHandler.mousePress(button);
    }
    
    default boolean mouseDown(MouseButtons button) 
    {
        return InputHandler.mouseDown(button);
    }
    
    default boolean mouseReleased(MouseButtons button) 
    {
        return InputHandler.mouseUp(button);
    }

    default boolean WheelMoved(MouseWheelDirection direction)
    {
        return InputHandler.WheelMoved(direction);
    }

    default int mouseX()
    {
        return InputHandler.mouseX + Renderer.camera.getX();
    }

    default int mouseY() 
    {
        return InputHandler.mouseY + Renderer.camera.getY();
    }
}