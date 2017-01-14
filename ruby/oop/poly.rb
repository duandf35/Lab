
require_relative '../can_run'

# OOP example in ruby
#
# see also:
# http://thoughts.codegram.com/understanding-class-instance-variables-in-ruby/
# see also:
# http://www.railstips.org/blog/archives/2006/11/18/class-and-instance-variables-in-ruby/
class Poly
  include CanRun

  # Constructor in ruby
  def initialize()
    Poly.static_check
    puts "(instance) Constructing class <#{self.class}>"
  end

  # Override <run> declared in <CanRun>
  def run
    cat = Cat.new
    dog = Dog.new
    duck = Duck.new
    goose = Goose.new

    cat.who_am_i
    dog.who_am_i
    duck.who_am_i
    goose.who_am_i

    puts "Number of <Animal> = #{Animal.animal_count}"
    puts "Number of <Mammal> = #{Mammal.mammal_count}"
    puts "Number of <Bird> = #{Bird.bird_count}"
    puts "Number of <Cat> = #{Cat.count}"
    puts "Number of <Dog> = #{Dog.count}"
    puts "Number of <Duck> = #{Duck.count}"
    puts "Number of <Goose> = #{Goose.count}"
  end

  # Duck typing
  #
  #          Animal
  #         /     \
  #    Mammal     Bird
  #    /    \     /  \
  #  Dog   Cat Duck  Goose
  #
  class Animal
    # Class hierarchy variable
    @@animal_count = 0

    # Define a class level instance variable
    #
    # 1. Define @count => an instance variable
    # 2. Define static getter self.count and setter self.count=(value) to update @count
    # 3. Update @count in the constructor by calling the setter
    @count = 0

    def initialize
      @@animal_count += 1
      self.class.count += 1
    end

    def self.animal_count
      @@animal_count
    end

    # The '=' make the method can be 'assigned'!!
    #
    # a = Animal.new
    # a.animal_count = 5
    # 'animal_count' is a function instead of a variable of the instance
    def self.animal_count=(value)
      @@animal_count = value
    end

    # Let every subclass inherit the <count> method for inheriting the variable @count
    # when any subclass invoke this method, it returns or initializes the instance variable
    # the subclass won't inherit the @count unless the attribute_accessor has been defined
    #
    # c = Cat.new
    # # without <count> method
    # c.count => nil
    # # with <count> method
    # c.count => 0
    def self.count
      @count ||= 0
    end

    def self.count=(value)
      @count = value
    end

    def who_am_i
      puts "This is <#{self.class}>"
    end
  end

  class Mammal < Animal
    @@mammal_count = 0

    def initialize
      super
      @@mammal_count += 1
    end

    def self.mammal_count
      @@mammal_count
    end

    def self.mammal_count=(value)
      @@mammal_count = value
    end
  end

  class Cat < Mammal; end

  class Dog < Mammal; end

  class Bird < Animal
    @@bird_count = 0

    def initialize
      super
      @@bird_count += 1
    end

    def self.bird_count
      @@bird_count
    end

    def self.bird_count=(value)
      @@bird_count = value
    end
  end

  class Duck < Bird; end

  class Goose < Bird; end
end
