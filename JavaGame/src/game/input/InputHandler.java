package game.input;

import game.Window;
import graphics.Renderer;

import java.awt.MouseInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;

import game.Gamecore;

/*
    keeps track of button states using awt keyevents
*/
public final class InputHandler extends KeyAdapter implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, Gamecore 
{

    private static LinkedList<Integer> ResetKeys = new LinkedList<Integer>();

    private static final int NUMBER_OF_KEYS = 114;

    private static boolean[] PressedKey = new boolean[NUMBER_OF_KEYS];
    private static boolean[] DownKey = new boolean[NUMBER_OF_KEYS];
    private static boolean[] ReleasedKey = new boolean[NUMBER_OF_KEYS];

    private static boolean[] PressedMouse = new boolean[5];
    private static boolean[] DownMouse = new boolean[5];
    private static boolean[] ReleasedMouse = new boolean[5];

    private static int WheelDirection = 0;
    private static boolean flagged = false; // this is to determine if to run reset loops

    public static int mouseX = 0;
    public static int mouseY = 0;

    public InputHandler()
    {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int index = e.getButton();
        try {
            if (!PressedMouse[index] && !DownMouse[index]) {
                PressedMouse[index] = true;
                DownMouse[index] = true;
                flagged = true;
            }

        } catch (IndexOutOfBoundsException e0) {

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int index = e.getButton();
        try {
            ReleasedMouse[index] = true;
            DownMouse[index] = false;
        } catch (IndexOutOfBoundsException e0) {

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        WheelDirection = e.getWheelRotation();
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int index = e.getKeyCode();
        try 
        {
            if (!PressedKey[index] && !DownKey[index]) 
            {
                PressedKey[index] = true;
                flagged = true;
                ResetKeys.add(index);
            }
            DownKey[index] = true;
        } catch (IndexOutOfBoundsException e0) {}
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int index = e.getKeyCode();
        try 
        {
            ReleasedKey[index] = true;
            DownKey[index] = false;
            flagged = true;
            ResetKeys.add(index); // add the released key to be reset
        } catch (IndexOutOfBoundsException e0) {}
    }

    public static void update() //updates states of pressed / released button states , as they go trough one 
    {
        mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();
        if (flagged) 
        {
            for (int i = 0; i < ResetKeys.size(); i++) 
            {
                int index = ResetKeys.get(i);
                PressedKey[index] = false;
                ReleasedKey[index] = false;
            }

            ResetKeys = new LinkedList<Integer>();

            for (int i = 0; i < PressedMouse.length; i++) //tiny array so no real need to only change certain indexs
            {
                PressedMouse[i] = false;
                ReleasedMouse[i] = false;
            }
            flagged = false;
        }

        WheelDirection = 0;
    }

    public static boolean WheelMoved(MouseWheelDirection direction) 
    {
        switch (direction) {
        case UP:
            return WheelDirection < 0;
        case DOWN:
            return WheelDirection > 0;
        case STATIC:
            return WheelDirection == 0;
        default:
            return false;
        }
    }

    public static int WheelDirection() 
    {
        return WheelDirection;
    }

    public static boolean mousePress(MouseButtons button) 
    {
        switch (button) 
        {
            case LEFT:
                return PressedMouse[1];
            case RIGHT:
                return PressedMouse[2];
            case MIDDLE:
                return PressedMouse[3];
            case MOUSE_4:
                return PressedMouse[4];
            case MOUSE_5:
                return PressedMouse[5];

        }

        return false;
    }

    public static boolean mouseDown(MouseButtons button) 
    {
        switch (button) 
        {
            case LEFT:
                return DownMouse[1];
            case RIGHT:
                return DownMouse[2];
            case MIDDLE:
                return DownMouse[3];
            case MOUSE_4:
                return DownMouse[4];
            case MOUSE_5:
                return DownMouse[5];
        }

        return false;
    }

    public static boolean mouseUp(MouseButtons button) 
    {
        switch (button) {
        case LEFT:
            return ReleasedMouse[1];
        case RIGHT:
            return ReleasedMouse[2];
        case MIDDLE:
            return ReleasedMouse[3];
        case MOUSE_4:
            return ReleasedMouse[4];
        case MOUSE_5:
            return ReleasedMouse[5];

        }

        return false;
    }

    public static boolean keyDown(int key) 
    {

        return DownKey[key];
    }

    public static boolean keyUp(int key) 
    {

        return ReleasedKey[key];
    }

    public static boolean keyPress(int key) 
    {

        return PressedKey[key];
    }

}
