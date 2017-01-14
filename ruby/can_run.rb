
module CanRun

  # class << self open up a singleton class
  # consider the method inside the block as a static method
  # same as defining self.included(base)
  class << self

    # Define the <included> method for inherit static method from
    # another class/module automatically when mixin happened
    #
    # When any class/module include this module, it will automatically
    # inherit the Runnable module
    #
    # Also the inherited method is static!!
    def included(base)
      base.extend Runnable
    end
  end

  module Runnable
    def static_check
      puts "(static) Inherit <static_check> method from <Runnable> when Mixin <CanRun> to class <#{self.class}>"
    end
  end

  # Only instance method can be included via mixin!!
  def run
    puts "(instance) Execute <run> in class <#{self.class}>"
  end
end
