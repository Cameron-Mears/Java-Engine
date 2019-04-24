
class Motor
{
    private:
        int pin;

    public:

    Motor(int pin)
    {
        this->pin = pin;
        pinMode(pin, OUTPUT);
    }
    
    void set(double power)
    {
        round(power * 255);
        analogWrite(pin, round(power * 255));
    }
};
Motor motor(13);
Motor botor(11);
void setup()
{
	
}

void loop()
{
	motor.set(0.1);
    botor.set(1);
}
